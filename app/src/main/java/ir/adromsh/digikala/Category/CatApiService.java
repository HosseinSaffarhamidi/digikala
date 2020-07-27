package ir.adromsh.digikala.Category;

import java.util.List;

import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class CatApiService {
    ApiService apiService = ApiProvider.apiProvider();

    public Single<List<Product>> getTabItem(String cat) {
        return apiService.getTabItem(cat);
    }
}
