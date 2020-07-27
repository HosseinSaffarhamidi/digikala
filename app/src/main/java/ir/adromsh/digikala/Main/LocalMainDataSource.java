package ir.adromsh.digikala.Main;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Banner;
import ir.adromsh.digikala.Model.Cat;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class LocalMainDataSource implements MainDataSource {

    @Override
    public Single<List<Product>> getProducts() {
        return null;
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return null;
    }

    @Override
    public Single<MyTimer> getTimer() {
        return null;
    }

    @Override
    public String getUserEmail() {
        return null;
    }

    @Override
    public Single<List<Cat>> getCats() {
        return null;
    }

    @Override
    public Single<Message> getCartCount(String email) {
        return null;
    }

    @Override
    public Single<List<Product>> search(String search) {
        return null;
    }
}
