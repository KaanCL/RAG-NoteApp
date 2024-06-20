package com.example.projecta.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecta.Model.Message;
import com.example.projecta.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.RowHolder>{

    List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,null);
        return new RowHolder(chatView);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(messageList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder{

        LinearLayout leftChatView , rightChatView;
        TextView leftTextView , rightTextView;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

            leftChatView = itemView.findViewById(R.id.left_chat_view);
            rightChatView = itemView.findViewById(R.id.right_chat_view);
            leftTextView = itemView.findViewById(R.id.left_chat_text_view);
            rightTextView = itemView.findViewById(R.id.right_chat_text_view);
        }

        public void bind (Message message , Integer posititon){

            if(message.getSentBy().equals(Message.SENT_BY_ME)){
                leftChatView.setVisibility(View.GONE);
                rightChatView.setVisibility(View.VISIBLE);
                rightTextView.setText(message.getMessage());
            }else{
                rightChatView.setVisibility(View.GONE);
                leftChatView.setVisibility(View.VISIBLE);
                leftTextView.setText(message.getMessage());

            }

        }


    }



}
