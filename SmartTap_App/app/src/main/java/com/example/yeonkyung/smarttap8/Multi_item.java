package com.example.yeonkyung.smarttap8;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nazzang on 2018-06-17.
 */

public class Multi_item implements Parcelable{
    private  String name, detail;
    private  int type;

    public Multi_item(String name, String detail){
        this.name = name;
        this.detail = detail;
        this.type = type;
    }


    protected Multi_item(Parcel in) {
        name = in.readString();
        detail = in.readString();
        type = in.readInt();
    }


    public static final Creator<Multi_item> CREATOR = new Creator<Multi_item>() {
        @Override
        public Multi_item createFromParcel(Parcel in) {
            return new Multi_item(in);
        }

        @Override
        public Multi_item[] newArray(int size) {
            return new Multi_item[size];
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
