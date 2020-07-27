
package ir.adromsh.digikala.Model;

import com.google.gson.annotations.SerializedName;

public class Favorite {

    @SerializedName("fav_id")
    private String mFavId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("product_id")
    private String mProductId;
    @SerializedName("title")
    private String mTitle;

    public String getFavId() {
        return mFavId;
    }

    public void setFavId(String favId) {
        mFavId = favId;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getProductId() {
        return mProductId;
    }

    public void setProductId(String productId) {
        mProductId = productId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
