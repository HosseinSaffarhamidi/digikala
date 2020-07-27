
package ir.adromsh.digikala.Model;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Basket {

    @SerializedName("basket_id")
    private String mBasketId;
    @SerializedName("guarantee")
    private String mGuarantee;
    @SerializedName("image")
    private String mImage;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("product_id")
    private String mProductId;
    @SerializedName("title")
    private String mTitle;

    public String getBasketId() {
        return mBasketId;
    }

    public void setBasketId(String basketId) {
        mBasketId = basketId;
    }

    public String getGuarantee() {
        return mGuarantee;
    }

    public void setGuarantee(String guarantee) {
        mGuarantee = guarantee;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
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
