package com.example.yeonkyung.smarttap8;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nazzang on 2018-06-14.
 */

public class Reservation_item implements Parcelable{
    private String timename, time1, time2;

//    private Boolean checked = false;

    public Reservation_item(Reservation_item r){
        this.timename = r.getTimename();
        this.time1 = r.getTime1();
        this.time2 = r.getTime2();
    }

    public Reservation_item(String timename, String time1, String time2){
        this.timename = timename;
        this.time1 = time1;
        this.time2 = time2;
    }

    protected Reservation_item(Parcel in) {
        timename = in.readString();
        time1 = in.readString();
        time2 = in.readString();
//        byte tmpChecked = in.readByte();
//        checked = tmpChecked == 0 ? null : tmpChecked == 1;
    }

    public static final Creator<Reservation_item> CREATOR = new Creator<Reservation_item>() {
        @Override
        public Reservation_item createFromParcel(Parcel in) {
            return new Reservation_item(in);
        }

        @Override
        public Reservation_item[] newArray(int size) {
            return new Reservation_item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timename);
        dest.writeString(time1);
        dest.writeString(time2);
//        dest.writeByte((byte) (checked == null ? 0 : checked ? 1 : 2));
    }

    public String getTimename() {
        return timename;
    }

    public void setTimename(String timename) {
        this.timename = timename;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }
}
