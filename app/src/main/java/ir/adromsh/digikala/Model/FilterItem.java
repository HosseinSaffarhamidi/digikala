
package ir.adromsh.digikala.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class FilterItem  {

    @SerializedName("title")
    private String mTitle;
    @SerializedName("values")
    private List<Value> mValues;


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<Value> getValues() {
        return mValues;
    }

    public void setValues(List<Value> values) {
        mValues = values;
    }

}
