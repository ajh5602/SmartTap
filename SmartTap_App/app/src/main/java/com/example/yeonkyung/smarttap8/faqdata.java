package com.example.yeonkyung.smarttap8;

import android.os.Parcel;
import android.os.Parcelable;

public class faqdata implements Parcelable{
    private String ftitle, ftext;
    private  int type;

    public faqdata(String ftitle, String ftext) {
        this.ftitle = ftitle;
        this.ftext = ftext;
        this.type = type;
    }

    protected faqdata(Parcel in) {
        ftitle = in.readString();
        ftext = in.readString();
        type = in.readInt();
    }

    public static final Creator<faqdata> CREATOR = new Creator<faqdata>() {
        @Override
        public faqdata createFromParcel(Parcel in) {
            return new faqdata(in);
        }

        @Override
        public faqdata[] newArray(int size) {
            return new faqdata[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ftitle);
        dest.writeString(ftext);
        dest.writeInt(type);
    }

    public String getFtitle() {
        return ftitle;
    }

    public void setFtitle(String ftitle) {
        this.ftitle = ftitle;
    }

    public String getFtext() {
        return ftext;
    }

    public void setFtext(String ftext) {
        this.ftext = ftext;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



}

