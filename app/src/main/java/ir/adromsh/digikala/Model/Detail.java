
package ir.adromsh.digikala.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;



public class Detail  {

    @SerializedName("colors")
    private String mColors;
    @SerializedName("comments")
    private List<Comment> mComments;
    @SerializedName("garantee")
    private String mGarantee;
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("introduction")
    private String mIntroduction;
    @SerializedName("pprice")
    private String mPprice;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("properties")
    private String mProperties;
    @SerializedName("rating")
    private String mRating;
    @SerializedName("rating_item")
    private String mRatingItem;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("weight")
    private String mWeight;
    @SerializedName("fav")
    private List<DetailFavorite> mFavList;

    public String getColors() {
        return mColors;
    }

    public void setColors(String colors) {
        mColors = colors;
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }

    public String getGarantee() {
        return mGarantee;
    }

    public void setGarantee(String garantee) {
        mGarantee = garantee;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public void setIntroduction(String introduction) {
        mIntroduction = introduction;
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

    public String getProperties() {
        return mProperties;
    }

    public void setProperties(String properties) {
        mProperties = properties;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getRatingItem() {
        return mRatingItem;
    }

    public void setRatingItem(String ratingItem) {
        mRatingItem = ratingItem;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getWeight() {
        return mWeight;
    }

    public void setWeight(String weight) {
        mWeight = weight;
    }

    public List<DetailFavorite> getmFavList() {
        return mFavList;
    }

    public void setmFavList(List<DetailFavorite> mFavList) {
        this.mFavList = mFavList;
    }
}
