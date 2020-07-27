
package ir.adromsh.digikala.Model;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Banner {

    @SerializedName("id")
    private String mId;
    @SerializedName("pic")
    private String mPic;
    @SerializedName("type")
    private String mType;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getPic() {
        return mPic;
    }

    public void setPic(String pic) {
        mPic = pic;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
