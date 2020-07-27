package ir.adromsh.digikala.CompareProduct;

import java.util.List;

import ir.adromsh.digikala.Model.Product;
import ir.adromsh.digikala.Model.Properties;
import io.reactivex.Single;

public class CompareViewModel {
    private CompareRepository repository = new CompareRepository();

    public Single<List<Properties>> getProperties() {
        return repository.getProperties();
    }
    public Single<List<Product>> getSearchedProduct(String search){
        return repository.getSearchedProduct(search);
    }
}
