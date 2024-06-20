const express = require("express")
const bodyParser = require("body-parser")
const dotenv = require("dotenv")
const mongoose = require("mongoose")
const cors= require('cors');
const noteRouter = require("./routers/note")
const chatRouter = require("./routers/chat")


const getEmbedding = require("./controllers/getEmbedding")
const postEmbedding = require("./controllers/postEmbedding")

const app = express();
dotenv.config();

app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended:true}))

const port = process.env.PORT || 5000

app.use(cors());
app.get("/",(req,res)=>{

    res.send("Nodejs server is running")
})


//app.use(getEmbedding)

app.use("/notes",noteRouter)
app.use("/chat",chatRouter)


mongoose
.connect(process.env.MONGO_URI,
    { useNewUrlParser: true, 
      useUnifiedTopology: true ,
})
.then(()=>{
    app.listen(port,()=>{
        console.log(`Server is running on port ${port}`)
        })
})
.catch((error)=>{
    console.error(error);
});




