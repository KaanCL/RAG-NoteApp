const Note = require("../models/note")
const postEmbedding = require("./postEmbedding");


const postNote = async (req, res) => {

    try {
        const note = new Note({
            date:req.body.date,
            titles:req.body.titles,
            text: req.body.text,
        });
        await note.save();
        postEmbedding(note)
        res.json(note);
    } catch (err) {
        res.status(500).json({error:err.message});
    }

  

}

module.exports=postNote