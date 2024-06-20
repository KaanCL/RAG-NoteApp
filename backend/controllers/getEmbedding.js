const { OpenAIEmbeddings } = require("@langchain/openai");
const { MongoDBAtlasVectorSearch } = require("@langchain/mongodb");
const { MongoClient } = require("mongodb");
const { ChatOpenAI } = require("@langchain/openai");
const { RetrievalQAChain } = require("langchain/chains");
const dotenv = require("dotenv");
dotenv.config();

const MONGO_URI = process.env.MONGO_URI;
if (!MONGO_URI) {
  console.log("Please add your Mongo URI to .env.local");
}

const getEmbedding = async (req, res) => {
  const client = new MongoClient(MONGO_URI, { useNewUrlParser: true, useUnifiedTopology: true });
  const dbName = "test";
  const collectionName = "notes_embedding";

  const query = req.body.query;

  try {
    await client.connect();
    const collection = client.db(dbName).collection(collectionName);

    const vectorStore = new MongoDBAtlasVectorSearch(
      new OpenAIEmbeddings({
        modelName: 'text-embedding-ada-002',
        stripNewLines: true,
        apiKey: process.env.OPEN_AI_KEY,
      }), {
        collection,
        indexName: "default",
        textKey: "text",
        embeddingKey: "embedding",
      });

    const retriever = vectorStore.asRetriever({
      searchType: "mmr",
      searchKwargs: {
        fetchK: 20,
        lambda: 0.1,
      },
    });

    const model = new ChatOpenAI({
      apiKey: process.env.OPEN_AI_KEY,
      modelName: 'gpt-3.5-turbo',
    });

    const chain = RetrievalQAChain.fromLLM(model, retriever);
    const chainResponse = await chain.call({ query: query });

    res.status(200).json(chainResponse);

  } catch (error) {
    console.error("Error fetching embeddings:", error);
    res.status(500).json({ error: "Error fetching embeddings", details: error.message });
  } finally {
    await client.close();
  }
};

module.exports = getEmbedding;
