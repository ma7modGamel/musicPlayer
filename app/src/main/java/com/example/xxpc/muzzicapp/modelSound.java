package com.example.xxpc.muzzicapp;

import android.os.Parcel;
import android.os.Parcelable;

public class modelSound implements Parcelable {
    int sound;


    public String getNameSound() {
        return nameSound;
    }


    public String getArtist() {
        return artist;
    }


    String nameSound;
    String artist;


    protected modelSound(Parcel in) {
        sound = in.readInt();
        nameSound = in.readString();
        artist = in.readString();
    }

    public static final Creator<modelSound> CREATOR = new Creator<modelSound>() {
        @Override
        public modelSound createFromParcel(Parcel in) {
            return new modelSound(in);
        }

        @Override
        public modelSound[] newArray(int size) {
            return new modelSound[size];
        }
    };

    public modelSound(int sound, String nameSound, String artist) {
        this.sound = sound;
        this.nameSound = nameSound;
        this.artist = artist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(sound);
        parcel.writeString(nameSound);
        parcel.writeString(artist);
    }
}
