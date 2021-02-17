package com.theo.videogameqizz;

import android.os.Parcelable;

public class Question {
    String question;
    int media;
    Answer answer;

    public String getQuestion() {
        return question;
    }

    public int getMedia() {
        return media;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Question(String question, int media, Answer answer) {
        this.question = question;
        this.media = media;
        this.answer = answer;
    }
}
