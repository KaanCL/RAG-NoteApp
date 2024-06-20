package com.example.projecta.Service;

import com.example.projecta.Model.Message;
import com.example.projecta.Model.Note;
import com.example.projecta.Model.Query;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.ArrayList;
import java.util.List;

public interface Service {

    @GET("/notes")
    Observable<ArrayList<Note>> getNotes();

    @POST("/notes")
    Call <Void> postNotes(@Body Note note);

    @GET("/notes/{id}")
    Observable <Note> getNoteById(@Path("id") String note_id);

    @DELETE("/notes/{id}")
    Call <Void> deleteNote(@Path("id") String note_id);

    @POST("/chat")
    Observable <Message> getMessage(@Body Query query);






}
