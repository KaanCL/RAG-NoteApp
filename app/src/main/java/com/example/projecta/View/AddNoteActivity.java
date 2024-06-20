package com.example.projecta.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projecta.Model.Note;
import com.example.projecta.R;
import com.example.projecta.ViewModel.NoteViewModel;

public class AddNoteActivity extends AppCompatActivity {

    EditText titleText , contentText;
    Dialog alert_dialog ;
    Button btnDialogDiscard  , btnDialogSave , btnDialogCancel  , btnDialogDelete  ;
    ImageView deleteButton;
    String noteId;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        titleText = findViewById(R.id.TitleText);
        contentText = findViewById(R.id.ContentText);
        deleteButton = findViewById(R.id.deleteButton);

        /*intentden note_id alınacak doluysa sorgu atılacak gelen veriler title ve texte yazılacak
          silme butonu aktif olacak
          alert dialogta put isteği atılacak
       */

        Intent intent = getIntent();

        noteId=intent.getStringExtra("noteId");

        System.out.println(noteId);

        if(noteId!=null && !noteId.isEmpty()){
            getNoteDetail();
            deleteButton.setVisibility(View.VISIBLE);

        }

        
    }

    public void openHome(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void openChatBot (View view){
        Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
        startActivity(intent);
    }

    public void saveNote(View view){

        alert_dialog = new Dialog(AddNoteActivity.this);
        alert_dialog.setContentView(R.layout.alert_layout);
        alert_dialog.setCancelable(false);

        btnDialogDiscard = alert_dialog.findViewById(R.id.discard_button);
        btnDialogSave = alert_dialog.findViewById(R.id.save_button);

        btnDialogDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_dialog.dismiss();
            }
        });

        btnDialogSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String noteTitle = titleText.getText().toString();
               String noteContent = contentText.getText().toString();

                if (noteTitle.length()==0|| noteContent.length()==0){
                    Toast toast = Toast.makeText(getApplicationContext(),"Title or content cannot be empty",Toast.LENGTH_LONG);
                    toast.show();
                    alert_dialog.dismiss();
                }
                else{
                    Note note = new Note(noteTitle,noteContent);
                    noteViewModel.postNote(note);
                    Toast toast = Toast.makeText(getApplicationContext(),"Note saved successfully",Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);


                }
            }
        });

alert_dialog.show();
    }

    public void deleteNote(View view){
        alert_dialog = new Dialog(AddNoteActivity.this);
        alert_dialog.setContentView(R.layout.alert_delete_layout);
        alert_dialog.setCancelable(false);

        btnDialogCancel= alert_dialog.findViewById(R.id.cancel_button);
        btnDialogDelete= alert_dialog.findViewById(R.id.delete_button);

       btnDialogCancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               alert_dialog.dismiss();

           }
       });

     btnDialogDelete.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             noteViewModel.deleteNote(noteId);
             Toast toast = Toast.makeText(getApplicationContext(),"Note deleted successfully",Toast.LENGTH_LONG);
             toast.show();
             Intent intent = new Intent(getApplicationContext(),MainActivity.class);
             startActivity(intent);
         }
     });


       alert_dialog.show();

    }

    public void getNoteDetail(){

        noteViewModel.getNoteDetail().observe(this,note -> {

            titleText.setText(note.getTitle());
            contentText.setText(note.getText());

        });

    }

}