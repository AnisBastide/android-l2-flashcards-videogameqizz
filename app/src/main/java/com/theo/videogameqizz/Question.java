package com.theo.videogameqizz;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable{
    String question;
    int media;
    Answer answer;

    protected Question(Parcel in) {
        question = in.readString();
        media = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeInt(media);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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
