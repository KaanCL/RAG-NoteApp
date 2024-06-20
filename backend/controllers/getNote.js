const Note = require("../models/note")

const getNote = async (req,res) =>{
    
    try{
        const note = await Note.find();
        res.status(200).json(note)

    }catch(err){
        res.status(404).json({message:err.message})

    }
};

const getNoteById= async(req,res)=>{
    const noteId = req.params.id

    try{
       const note=await Note.findById(noteId);
       if(note){
        res.status(200).json(note);
       }
       else{
        res.status(404).json({message:"Note not found"})
       }
    }catch (err) {
        res.status(404).json({ message: err.message });
    }

}




module.exports = {
    getNote,
    getNoteById,


};