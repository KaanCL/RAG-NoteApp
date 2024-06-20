const Note = require("../models/note")


const deleteNote = async (req,res)=>{
    const noteId= req.params.id

    try {

        const result = await Note.findByIdAndDelete(noteId);

        if (result) {
            res.status(200).json({ message: "Not başarıyla silindi", result });
        } else {
            res.status(404).json({ message: "Note not found" });
        }
    } catch (err) {
        res.status(500).json({ message: err.message });
    }
}




module.exports=deleteNote