
package ir.adromsh.digikala.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Cat implements Parcelable {

    @SerializedName("id")
    private String mId;
    @SerializedName("parent")
    private String mParent;
    @SerializedName("position")
    private String mPosition;
    @SerializedName("title")
    private String mTitle;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getParent() {
        return mParent;
    }

    public void setParent(String parent) {
        mParent = parent;
    }

    public String getPicurl() {
        return mPosition;
    }

    public void setPicurl(String picurl) {
        mPosition = picurl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mParent);
        dest.writeString(this.mPosition);
        dest.writeString(this.mTitle);
    }

    public Cat() {
    }

    protected Cat(Parcel in) {
        this.mId = in.readString();
        this.mParent = in.readString();
        this.mPosition = in.readString();
        this.mTitle = in.readString();
    }

    public static final Parcelable.Creator<Cat> CREATOR = new Parcelable.Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel source) {
            return new Cat(source);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };
}
