package com.example.projecta.Respository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projecta.Model.Message;
import com.example.projecta.Model.Query;
import com.example.projecta.Service.RetrofitRequest;
import com.example.projecta.Service.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.projecta.Model.Note;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NoteRespository {
    public static final String TAG = NoteRespository.class.getSimpleName();
    private Service service;
    private CompositeDisposable compositeDisposable;

    public NoteRespository() {
        service = RetrofitRequest.getRetrofitInstance().create(Service.class);
        compositeDisposable = new CompositeDisposable();

    }

    public LiveData<ArrayList<Note>> getNotes() {
        final MutableLiveData<ArrayList<Note>> data = new MutableLiveData<>();
        compositeDisposable.add(service.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        note -> data.setValue(note),
                        throwable -> {

                            System.out.println("Hata: " + throwable);

                        }
                ));

        return data;
    }

    public void postNotes(Note note){

        Call<Void> call = service.postNotes(note);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    System.out.println("Note created successfully");
                } else {
                    System.out.println("Request failed: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());

            }
        });


    }

    public LiveData<Note> getNotesById(String note_id){
        final MutableLiveData<Note> data = new MutableLiveData<>();
        try {
            compositeDisposable.add(service.getNoteById(note_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            note -> {
                                data.setValue(note);},
                            throwable -> {System.out.println("Hata: " + throwable);}
                    ));

        }catch (Exception e){
            System.out.println("Hata !"+e);

        }

        return data;

    }

    public void deleteNote(String note_id){
        Call<Void> call = service.deleteNote(note_id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) { if (response.isSuccessful()) {
                System.out.println("Note Deleted successfully");
            } else {
                System.out.println("Request failed: " + response.code());
            }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });

    }

    public LiveData<Message> getMessage(Query query){
        final MutableLiveData<Message> data = new MutableLiveData<>();
        try {
            compositeDisposable.add(service.getMessage(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            message -> {
                                data.setValue(message);
                            },
                            throwable -> {

                                System.out.println("Hata: " + throwable);
                            }
                            ));


        }catch (Exception e){
            System.out.println("Hata !"+e);

        }
        return data;
    }

}
