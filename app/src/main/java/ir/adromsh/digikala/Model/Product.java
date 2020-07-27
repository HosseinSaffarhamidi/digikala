
package ir.adromsh.digikala.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Product {

    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mPic;
    @SerializedName("pprice")
    private String mPprice;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("properties")
    private String mProperties;
    @SerializedName("filter_item")
    private String mFilterItem;



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

    public String getPprice() {
        return mPprice;
    }

    public void setPprice(String pprice) {
        mPprice = pprice;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getmProperties() {
        return mProperties;
    }

    public void setmProperties(String mProperties) {
        this.mProperties = mProperties;
    }

    public String getmFilterItem() {
        return mFilterItem;
    }

    public void setmFilterItem(String mFilterItem) {
        this.mFilterItem = mFilterItem;
    }


}
