package ir.adromsh.digikala.Filter;

import org.json.JSONObject;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;
import retrofit2.http.Body;

public interface FilterDataSource  {
    public Single<List<Product>> getTabItem(String cat);
    public Single<List<Product>> getSortedList(String cat,int sort);
    public Single<Message> sendFilterParam(@Body List<JSONObject> jsonObjects);
}
