package ir.adromsh.digikala.Main;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Banner;
import ir.adromsh.digikala.Model.Cat;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public interface MainDataSource {

    Single<List<Product>> getProducts();

    Single<List<Banner>> getBanners();

    Single<MyTimer> getTimer();

    String getUserEmail();

    Single<List<Cat>> getCats();

    Single<Message> getCartCount(String email);

    Single<List<Product>> search(String search);
}
