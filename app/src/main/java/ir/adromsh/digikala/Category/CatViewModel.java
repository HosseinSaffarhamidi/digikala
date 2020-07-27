package ir.adromsh.digikala.Category;

import java.util.List;

import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class CatViewModel {
    private CatRepository repository=new CatRepository();

    public Single<List<Product>> getTabItem(String cat){
        return repository.getTabItem(cat);
    }
}
