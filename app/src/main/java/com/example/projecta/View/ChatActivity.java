package com.example.projecta.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.projecta.Adapter.MessageAdapter;
import com.example.projecta.Model.Message;
import com.example.projecta.Model.Query;
import com.example.projecta.R;
import com.example.projecta.ViewModel.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editMessage;
    ImageButton btnSend;

    List<Message> messageList;

    MessageAdapter messageAdapter;

    private NoteViewModel noteViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.chat_rv);
        editMessage = findViewById(R.id.message_edit_text);
        btnSend = findViewById(R.id.send_btn);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);

        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = editMessage.getText().toString().trim();
                Query query = new Query(question);
                addToChat(question,Message.SENT_BY_ME);
                getAIMessage(query);
                editMessage.setText("");

            }
        });



    }

    public void addToChat(String message,String sentBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message,sentBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });


    }


    public void getAIMessage(Query query){

        noteViewModel.getMessages(query).observe(this,message -> {
           addToChat(message.getMessage(),Message.SENT_BY_BOT);
        });

    }

    public void openHome(View v){

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }




}


