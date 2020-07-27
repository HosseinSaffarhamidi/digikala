
package ir.adromsh.digikala.Model;


import com.google.gson.annotations.SerializedName;


public class Properties {

    @SerializedName("title")
    private String mTitle;
    @SerializedName("value")
    private String mValue;
    @SerializedName("second")
    private String mSecond;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getmSecond() {
        return mSecond;
    }

    public void setmSecond(String mSecond) {
        this.mSecond = mSecond;
    }
}
