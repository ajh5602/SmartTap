package com.example.yeonkyung.smarttap8;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nazzang on 2018-06-20.
 */

public class Equi_List_item implements Parcelable{

    private String equiname, equidetail, equicurrent, equimonth, equicurrentM;

    public Equi_List_item(Equi_List_item e){
        this.equiname = e.getEquiname();
        this.equidetail = e.getEquidetail();
        this.equicurrent = e.getEquicurrent();
        this.equimonth = e.getEquimonth();
        this.equicurrentM = e.getEquicurrentM();
    }

    public Equi_List_item(String equiname, String equidetail, String equicurrent, String equimonth, String equicurrentM){
        this.equiname = equiname;
        this.equidetail = equidetail;
        this.equicurrent = equicurrent;
        this.equimonth = equimonth;
        this.equicurrentM = equicurrentM;
    }

    protected Equi_List_item(Parcel in) {
        equiname = in.readString();
        equidetail = in.readString();
        equicurrent = in.readString();
        equimonth = in.readString();
        equicurrentM = in.readString();
    }

    public static final Creator<Equi_List_item> CREATOR = new Creator<Equi_List_item>() {
        @Override
        public Equi_List_item createFromParcel(Parcel in) {
            return new Equi_List_item(in);
        }

        @Override
        public Equi_List_item[] newArray(int size) {
            return new Equi_List_item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(equiname);
        dest.writeString(equidetail);
        dest.writeString(equicurrent);
        dest.writeString(equimonth);
        dest.writeString(equicurrentM);
    }

    public String getEquiname() {
        return equiname;
    }

    public void setEquiname(String equiname) {
        this.equiname = equiname;
    }

    public String getEquidetail() {
        return equidetail;
    }

    public void setEquidetail(String equidetail) {
        this.equidetail = equidetail;
    }

    public String getEquicurrent() {
        return equicurrent;
    }

    public void setEquicurrent(String equicurrent) {
        this.equicurrent = equicurrent;
    }

    public String getEquimonth() {
        return equimonth;
    }

    public void setEquimonth(String equimonth) {
        this.equimonth = equimonth;
    }

    public String getEquicurrentM() {
        return equicurrentM;
    }

    public void setEquicurrentM(String equicurrentM) {
        this.equicurrentM = equicurrentM;
    }
}
