package com.example.projecta.Model;

import java.util.ArrayList;

public class Note {

    private String _id;
    private String titles ;
    private String date;
    private String text ;

    public Note( String titles,String text) {
        this.titles = titles;
        this.text = text;
    }

    public String getTitle() {
        return titles;
    }


    public String getDate() {
        return date;
    }

    public String get_id() {
        return _id;
    }

    public String getText() {
        return text;
    }




}
