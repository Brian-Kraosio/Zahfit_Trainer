package com.example.zahfit_trainer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonalTrainer implements Parcelable  {
    private String personal_trainer_name;
    private String personal_trainer_ratings;

    public PersonalTrainer() {
    }

    public PersonalTrainer(String personal_trainer_name) {
        this.personal_trainer_name = personal_trainer_name;
    }

    public String getPersonal_trainer_name() {
        return personal_trainer_name;
    }

    public void setPersonal_trainer_name(String personal_trainer_name) {
        this.personal_trainer_name = personal_trainer_name;
    }

    public String getPersonal_trainer_ratings() {
        return personal_trainer_ratings;
    }

    public void setPersonal_trainer_ratings(String personal_trainer_ratings) {
        this.personal_trainer_ratings = personal_trainer_ratings;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.personal_trainer_name);
        dest.writeString(this.personal_trainer_ratings);
    }

    protected PersonalTrainer(Parcel in) {
        this.personal_trainer_name = in.readString();
        this.personal_trainer_ratings = in.readString();
    }

    public static final Creator<PersonalTrainer> CREATOR = new Creator<PersonalTrainer>() {
        @Override
        public PersonalTrainer createFromParcel(Parcel source) {
            return new PersonalTrainer(source);
        }

        @Override
        public PersonalTrainer[] newArray(int size) {
            return new PersonalTrainer[size];
        }
    };
}
