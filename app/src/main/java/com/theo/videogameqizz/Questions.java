package com.theo.videogameqizz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions implements Parcelable {
    private List<Question> questions = new ArrayList<>();
    private Integer index = 0;
    private Integer points = 0;
    private String difficulty;

    protected Questions(Parcel in) {
        questions = in.createTypedArrayList(Question.CREATOR);
        if (in.readByte() == 0) {
            index = null;
        } else {
            index = in.readInt();
        }
        if (in.readByte() == 0) {
            points = null;
        } else {
            points = in.readInt();
        }
        difficulty = in.readString();
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

    public Integer getPoints() {
        return points;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void incrementIndex() {
        index++;
    }

    public void incrementPoints() {
        points++;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }

    public Questions(String difficulty, Question... questions) {
        this.difficulty = difficulty;
        for (Question question : questions) {
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
        if (points == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(points);
        }
        dest.writeString(difficulty);
    }
}
