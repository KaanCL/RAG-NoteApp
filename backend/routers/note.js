const express = require("express")

const postNote = require("../controllers/postNote")
const {getNote,getNoteById,getNoteByTitle} = require("../controllers/getNote")
const deleteNote = require("../controllers/deleteNote")



const router = express.Router();

router.get("/",getNote)
router.get("/:id",getNoteById)
router.post("/",postNote)
router.delete("/:id",deleteNote)


module.exports = router