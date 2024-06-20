package com.example.projecta.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecta.Model.Note;
import com.example.projecta.R;
import com.example.projecta.Util.Credentials;
import com.example.projecta.View.AddNoteActivity;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.RowHolder>{

    private ArrayList<Note> notes;

    public NoteAdapter(ArrayList<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.note_layout,parent,false);
        return new RowHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(notes.get(position),position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), AddNoteActivity.class);
                intent.putExtra("noteId",notes.get(position).get_id());
                Credentials.setNoteId(notes.get(position).get_id());
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder{

        TextView titles_text , content_text , date_text;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind (Note note , Integer Position){

            String date = note.getDate();
            String redate = "";

            for(int i = 0 ; i<10 ; i ++){
                redate += date.charAt(i);
            }

            titles_text = itemView.findViewById(R.id.titles_text);
            content_text = itemView.findViewById(R.id.content_text);
            date_text = itemView.findViewById(R.id.date_text);

            titles_text.setText(note.getTitle());
            date_text.setText(redate);



            String recontent_text = note.getText();
            if (recontent_text.length() > 250) {
                recontent_text = recontent_text.substring(0, 250) + "...";
            }

            content_text.setText(recontent_text);

        }

    }


}
