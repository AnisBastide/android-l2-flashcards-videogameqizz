package com.theo.videogameqizz;

import android.os.Parcel;
import android.os.Parcelable;

public class Media implements Parcelable {
    private String type;
    private String link;

    protected Media(Parcel in) {
        type = in.readString();
        link = in.readString();
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public Media(String type, String link) {
        this.type = type;
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(link);
    }
}
