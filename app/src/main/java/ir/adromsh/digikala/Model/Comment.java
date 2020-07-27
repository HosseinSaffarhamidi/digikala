
package ir.adromsh.digikala.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Comment implements Parcelable {

    @SerializedName("dislikecount")
    private String mDislikecount;
    @SerializedName("id")
    private String mId;
    @SerializedName("likecount")
    private String mLikecount;
    @SerializedName("negative")
    private String mNegative;
    @SerializedName("param")
    private String mParam;
    @SerializedName("passage")
    private String mPassage;
    @SerializedName("positive")
    private String mPositive;
    @SerializedName("suggest")
    private String mSuggest;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("user")
    private String mUser;

    public String getDislikecount() {
        return mDislikecount;
    }

    public void setDislikecount(String dislikecount) {
        mDislikecount = dislikecount;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLikecount() {
        return mLikecount;
    }

    public void setLikecount(String likecount) {
        mLikecount = likecount;
    }

    public String getNegative() {
        return mNegative;
    }

    public void setNegative(String negative) {
        mNegative = negative;
    }

    public String getParam() {
        return mParam;
    }

    public void setParam(String param) {
        mParam = param;
    }

    public String getPassage() {
        return mPassage;
    }

    public void setPassage(String passage) {
        mPassage = passage;
    }

    public String getPositive() {
        return mPositive;
    }

    public void setPositive(String positive) {
        mPositive = positive;
    }

    public String getSuggest() {
        return mSuggest;
    }

    public void setSuggest(String suggest) {
        mSuggest = suggest;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDislikecount);
        dest.writeString(this.mId);
        dest.writeString(this.mLikecount);
        dest.writeString(this.mNegative);
        dest.writeString(this.mParam);
        dest.writeString(this.mPassage);
        dest.writeString(this.mPositive);
        dest.writeString(this.mSuggest);
        dest.writeString(this.mTitle);
        dest.writeString(this.mUser);
    }

    public Comment() {
    }

    protected Comment(Parcel in) {
        this.mDislikecount = in.readString();
        this.mId = in.readString();
        this.mLikecount = in.readString();
        this.mNegative = in.readString();
        this.mParam = in.readString();
        this.mPassage = in.readString();
        this.mPositive = in.readString();
        this.mSuggest = in.readString();
        this.mTitle = in.readString();
        this.mUser = in.readString();
    }

    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
