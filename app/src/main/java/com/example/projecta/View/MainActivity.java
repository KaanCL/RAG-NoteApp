package com.example.projecta.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projecta.Adapter.NoteAdapter;
import com.example.projecta.Model.Note;
import com.example.projecta.R;
import com.example.projecta.Respository.NoteRespository;
import com.example.projecta.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    ArrayList<Note> notes;
    private NoteViewModel noteViewModel;
    RecyclerView recyclerView_notes;
    NoteAdapter noteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView_notes = findViewById(R.id.note_recyclerView);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);



        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext() , AddNoteActivity.class);
                startActivity(intent);
            }
        });


        getNotes();


    }

    public void getNotes(){
        noteViewModel.getNoteList().observe(this,notes -> {
            notes = new ArrayList<Note>(notes);
            ArrayList<Note> reversedNotes = new ArrayList<>();



            for (int i = notes.size()-1 ; i>=0 ;i--){
                reversedNotes.add(notes.get(i));
            }


            recyclerView_notes.setLayoutManager(new LinearLayoutManager(MainActivity.this ,LinearLayoutManager.VERTICAL, false));
            noteAdapter = new NoteAdapter(reversedNotes);
            recyclerView_notes.setAdapter(noteAdapter);

        });


    }

    public void openAddNote (View view){
        Intent intent = new Intent(getApplicationContext(),AddNoteActivity.class);
        startActivity(intent);

    }
    public void openChatBot (View view){
        Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
        startActivity(intent);

    }

}