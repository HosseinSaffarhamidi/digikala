package ir.adromsh.digikala.Filter;

import org.json.JSONObject;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class FilterViewModel {
    private FilterRepository repository = new FilterRepository();

    public Single<List<Product>> getTabItem(String cat) {
        return repository.getTabItem(cat);
    }
    public Single<List<Product>> getSortedList(String cat,int sort){
        return repository.getSortedList(cat, sort);
    }

    public Single<Message> sendFilterParam(List<JSONObject> jsonObjects){
        return repository.sendFilterParam(jsonObjects);
    }
}
