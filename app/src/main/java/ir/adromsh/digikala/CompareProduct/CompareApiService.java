package ir.adromsh.digikala.CompareProduct;

import java.util.List;

import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.Product;
import ir.adromsh.digikala.Model.Properties;
import io.reactivex.Single;

public class CompareApiService implements CompareDataSource {
    ApiService apiService= ApiProvider.apiProvider();
    @Override
    public Single<List<Properties>> getProperties() {
        return apiService.getProperties();
    }

    @Override
    public Single<List<Product>> getSearchedProduct(String search) {
        return apiService.getSearchedProduct(search);
    }
}
