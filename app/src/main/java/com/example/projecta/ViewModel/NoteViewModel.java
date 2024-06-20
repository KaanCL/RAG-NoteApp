package com.example.projecta.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.projecta.Model.Message;
import com.example.projecta.Model.Note;

import java.util.ArrayList;
import java.util.List;

import com.example.projecta.Model.Query;
import com.example.projecta.Respository.NoteRespository;
import com.example.projecta.Util.Credentials;

public class NoteViewModel extends AndroidViewModel {
    private NoteRespository noteRespository;
    private LiveData<ArrayList<Note>> noteList ;
    private LiveData<Note> noteDetail;
    private LiveData<Message> messages;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRespository = new NoteRespository();
        noteList = noteRespository.getNotes();
        noteDetail = noteRespository.getNotesById(Credentials.getNoteId());

    }

    public LiveData<ArrayList<Note>> getNoteList() {
        return noteList;
    }

    public void postNote(Note note){noteRespository.postNotes(note);}

    public LiveData<Note> getNoteDetail() {
        return noteDetail;
    }

    public void deleteNote(String note_id){noteRespository.deleteNote(note_id);}

    public LiveData<Message> getMessages(Query query){return noteRespository.getMessage(query);}



}
