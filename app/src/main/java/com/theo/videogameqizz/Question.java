package com.theo.videogameqizz;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable{
    String question;
    Media media;
    Answer answer;


    protected Question(Parcel in) {
        question = in.readString();
        media = in.readParcelable(Media.class.getClassLoader());
        answer = in.readParcelable(Answer.class.getClassLoader());
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

    public Media getMedia() {
        return media;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Question(String question, Media media, Answer answer) {
        this.question = question;
        this.media = media;
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeParcelable(media, flags);
        dest.writeParcelable(answer, flags);
    }
}
