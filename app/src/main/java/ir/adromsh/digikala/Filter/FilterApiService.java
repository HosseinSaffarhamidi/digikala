package ir.adromsh.digikala.Filter;

import org.json.JSONObject;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class FilterApiService implements FilterDataSource {
    ApiService apiService = ApiProvider.apiProvider();


    @Override
    public Single<List<Product>> getTabItem(String cat) {
        return apiService.getTabItem(cat);
    }

    @Override
    public Single<List<Product>> getSortedList(String cat, int sort) {
        return apiService.getSortedList(cat, sort);
    }

    @Override
    public Single<Message> sendFilterParam(List<JSONObject> jsonObjects) {
        return apiService.sendFilterParam(jsonObjects);
    }


}
