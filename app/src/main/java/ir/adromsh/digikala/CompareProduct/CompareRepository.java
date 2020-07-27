package ir.adromsh.digikala.CompareProduct;

import java.util.List;

import ir.adromsh.digikala.Model.Product;
import ir.adromsh.digikala.Model.Properties;
import io.reactivex.Single;

public class CompareRepository implements CompareDataSource {
    CompareApiService compareApiService=new CompareApiService();
    @Override
    public Single<List<Properties>> getProperties() {
        return compareApiService.getProperties();
    }

    @Override
    public Single<List<Product>> getSearchedProduct(String search) {
        return compareApiService.getSearchedProduct(search);
    }
}
