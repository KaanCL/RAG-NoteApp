package com.example.projecta.Model;

import com.google.gson.annotations.SerializedName;

public class Message {

    public static String SENT_BY_ME ="me";
    public static String SENT_BY_BOT = "bot";

    @SerializedName("text")
    String message ;
    String sentBy;

    public Message(String message, String sentBy) {
        this.message = message;
        this.sentBy = sentBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSendby(String sentBy) {
        this.sentBy = sentBy;
    }
}

