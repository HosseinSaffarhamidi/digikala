package ir.adromsh.digikala.CompareProduct;

import java.util.List;

import ir.adromsh.digikala.Model.Product;
import ir.adromsh.digikala.Model.Properties;
import io.reactivex.Single;

public interface CompareDataSource {
    Single<List<Properties>> getProperties();
    Single<List<Product>> getSearchedProduct(String search);
}
