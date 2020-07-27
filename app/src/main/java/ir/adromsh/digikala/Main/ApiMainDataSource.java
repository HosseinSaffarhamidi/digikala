package ir.adromsh.digikala.Main;


import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.Banner;
import ir.adromsh.digikala.Model.Cat;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class ApiMainDataSource implements MainDataSource {

    private ApiService apiService=ApiProvider.apiProvider();

    @Override
    public Single<List<Product>> getProducts() {
        return apiService.getProducts();
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return apiService.getBanners();
    }

    @Override
    public Single<MyTimer> getTimer() {
        return apiService.getTimer();
    }

    @Override
    public String getUserEmail() {
        return null;
    }

    @Override
    public Single<List<Cat>> getCats() {
        return apiService.getCats();
    }

    @Override
    public Single<Message> getCartCount(String email) {
        return apiService.getBasketCount(email);
    }

    @Override
    public Single<List<Product>> search(String search) {
        return apiService.getSearchedProduct(search);
    }
}
