package ir.adromsh.digikala.Category;

import java.util.List;

import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class CatRepository {
    CatApiService catApiService=new CatApiService();

    public Single<List<Product>> getTabItem(String cat){
        return catApiService.getTabItem(cat);
    }
}
