package com.theo.videogameqizz;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    private String goodAnswer;
    private String otherAnswerOne;
    private String otherAnswerTwo;

    protected Answer(Parcel in) {
        goodAnswer = in.readString();
        otherAnswerOne = in.readString();
        otherAnswerTwo = in.readString();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public String getGoodAnswer() {
        return goodAnswer;
    }

    public String getOtherAnswerOne() {
        return otherAnswerOne;
    }

    public String getOtherAnswerTwo() {
        return otherAnswerTwo;
    }

    public Answer(String goodAnswer, String otherAnswerOne, String otherAnswerTwo) {
        this.goodAnswer = goodAnswer;
        this.otherAnswerOne = otherAnswerOne;
        this.otherAnswerTwo = otherAnswerTwo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(goodAnswer);
        dest.writeString(otherAnswerOne);
        dest.writeString(otherAnswerTwo);
    }
}
