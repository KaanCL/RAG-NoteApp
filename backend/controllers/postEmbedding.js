const { MongoClient } = require("mongodb") ;
const { OpenAIEmbeddings } = require("@langchain/openai");


const dotenv = require("dotenv");
const { response } = require("express");
const getEmbedding = require("./getEmbedding");
const { text } = require("body-parser");

dotenv.config();


const MONGO_URI =process.env.MONGO_URI
const client = new MongoClient(MONGO_URI)


const connectDB = async() =>{
    await client.connect();
    console.log("Connected to MongoDB");
    const db = client.db("test");
    const collection = db.collection("notes_embedding");
    return collection;
}

const createEmbedding = async(text)=>{
    const response = await new OpenAIEmbeddings({
        modelName: 'text-embedding-ada-002',
        apiKey : process.env.OPEN_AI_KEY,
        stripNewLines:true,  }).embedQuery(text);

    return response
}

const postEmbedding = async(note)=>{
    const collection = await connectDB();

    const text = (note.titles + note.text)
    console.log(text)
       const embedding = await createEmbedding(text);
    
        const document = {
          text: text,
          embedding: embedding,
        };

        await collection.insertOne(document);
        console.log(`Saved embedding for text: ${text}`);
  

    console.log("All embeddings have been saved.");
}
module.exports = postEmbedding