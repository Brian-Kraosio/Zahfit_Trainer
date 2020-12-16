package com.example.zahfit_trainer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Plan implements Parcelable {
    String plan_key, plan_name, level_name, type_name, personal_trainer_id, uri;

    protected Plan(Parcel in) {
        plan_key = in.readString();
        plan_name = in.readString();
        level_name = in.readString();
        type_name = in.readString();
        personal_trainer_id = in.readString();
        uri = in.readString();
    }

    public String getPlan_key() {
        return plan_key;
    }

    public void setPlan_key(String plan_key) {
        this.plan_key = plan_key;
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Plan() {
    }

    public Plan(String plan_name, String level_name
            , String type_name, String personal_trainer_id, String uri) {
        this.plan_name = plan_name;
        this.level_name
                = level_name
        ;
        this.type_name = type_name;
        this.personal_trainer_id = personal_trainer_id;
        this.uri = uri;
    }

    public Plan(String plan_name, String level_name
            , String type_name, String personal_trainer_id) {
        this.plan_name = plan_name;
        this.level_name
                = level_name
        ;
        this.type_name = type_name;
        this.personal_trainer_id = personal_trainer_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public String getLevel_name
            () {
        return level_name
                ;
    }

    public void setLevel_name
            (String level_name
            ) {
        this.level_name
                = level_name
        ;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getPersonal_trainer_id() {
        return personal_trainer_id;
    }

    public void setPersonal_trainer_id(String personal_trainer_id) {
        this.personal_trainer_id = personal_trainer_id;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(plan_key);
        parcel.writeString(plan_name);
        parcel.writeString(level_name);
        parcel.writeString(type_name);
        parcel.writeString(personal_trainer_id);
        parcel.writeString(uri);
    }
}
