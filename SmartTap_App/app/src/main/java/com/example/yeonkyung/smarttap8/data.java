package com.example.yeonkyung.smarttap8;


import android.os.Parcel;
import android.os.Parcelable;

public class data implements Parcelable{
    private  String name, detail;
    private  int type;


    public data(String name, String detail){
        this.name = name;
        this.detail = detail;
        this.type = type;
    }


    protected data(Parcel in) {
        name = in.readString();
        detail = in.readString();
        type = in.readInt();
    }

    public static final Creator<data> CREATOR = new Creator<data>() {
        @Override
        public data createFromParcel(Parcel in) {
            return new data(in);
        }

        @Override
        public data[] newArray(int size) {
            return new data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(detail);
        dest.writeInt(type);
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
