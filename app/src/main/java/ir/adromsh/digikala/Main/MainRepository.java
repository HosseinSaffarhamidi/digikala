package ir.adromsh.digikala.Main;

import android.content.Context;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Login.UserLoginInfo;
import ir.adromsh.digikala.Model.Banner;
import ir.adromsh.digikala.Model.Cat;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class MainRepository implements MainDataSource {
    private Context context;
    private ApiMainDataSource apiMainDataSource = new ApiMainDataSource();
    private LocalMainDataSource localMainDataSource = new LocalMainDataSource();
    private UserLoginInfo userLoginInfo;

    public MainRepository(Context context) {
        this.context = context;
        userLoginInfo = new UserLoginInfo(context);
    }

    @Override
    public Single<List<Product>> getProducts() {
        return apiMainDataSource.getProducts();
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return apiMainDataSource.getBanners();
    }

    @Override
    public Single<MyTimer> getTimer() {
        return apiMainDataSource.getTimer();
    }

    @Override
    public String getUserEmail() {
        return userLoginInfo.getUserEmail();
    }

    @Override
    public Single<List<Cat>> getCats() {
        return apiMainDataSource.getCats();
    }

    @Override
    public Single<Message> getCartCount(String email) {
        return apiMainDataSource.getCartCount(email);
    }

    @Override
    public Single<List<Product>> search(String search) {
        return apiMainDataSource.search(search);
    }

}
