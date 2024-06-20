const mongoose = require("mongoose")

const NoteSchema = new mongoose.Schema({
      date:{type:Date,default:Date.now,required:true},
      titles:{type:String,required:true },
      text:{type:String,required:true}
})

const Note = mongoose.model("Note",NoteSchema)
module.exports = Note
