
package ir.adromsh.digikala.Model;


import com.google.gson.annotations.SerializedName;


public class Param {

    @SerializedName("design_quality")
    private String mDesignQuality;

    public String getDesignQuality() {
        return mDesignQuality;
    }

    public void setDesignQuality(String designQuality) {
        mDesignQuality = designQuality;
    }

}
