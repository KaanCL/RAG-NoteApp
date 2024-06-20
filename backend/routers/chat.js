const express = require("express")

const getEmbedding = require("../controllers/getEmbedding");
const { route } = require("./note");

const router = express.Router();


router.post("/",getEmbedding)



module.exports = router