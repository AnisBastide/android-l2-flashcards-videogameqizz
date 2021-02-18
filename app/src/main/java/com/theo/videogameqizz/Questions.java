package com.theo.videogameqizz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions implements Parcelable {
    private List<Question> questions = new ArrayList<>();
    private Integer index = 0;

    protected Questions(Parcel in) {
        questions = in.createTypedArrayList(Question.CREATOR);
        if (in.readByte() == 0) {
            index = null;
        } else {
            index = in.readInt();
        }
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    public Integer getIndex() {
        return index;
    }
    public void incrementIndex(){
        index++;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public void shuffleQuestions(){
        Collections.shuffle(questions);
    }

    public Questions(Question ...questions) {
        for (Question question:questions) {
            this.questions.add(question);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(questions);
        if (index == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(index);
        }
    }
}
