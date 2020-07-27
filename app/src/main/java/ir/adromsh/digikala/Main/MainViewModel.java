package ir.adromsh.digikala.Main;

import android.content.Context;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Login.UserLoginInfo;
import ir.adromsh.digikala.Model.Banner;
import ir.adromsh.digikala.Model.Cat;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class MainViewModel {
    private Context context;
    MainRepository mainRepository;
    UserLoginInfo userLoginInfo;

    public MainViewModel(Context context) {
        this.context = context;
        userLoginInfo = new UserLoginInfo(context);
        mainRepository = new MainRepository(context);
    }

    public Single<List<Product>> getProdcts() {
        return mainRepository.getProducts();
    }

    public Single<List<Banner>> getBanners() {
        return mainRepository.getBanners();
    }

    public Single<MyTimer> getTimer() {
        return mainRepository.getTimer();
    }

    public void saveEmailData(String email) {
        userLoginInfo.saveLoginData(email);
    }

    public String getUserEmail() {
        return mainRepository.getUserEmail();
    }

    public Single<List<Cat>> getCats() {
        return mainRepository.getCats();
    }

    public Single<Message> getBasketCount(String email) {
        return mainRepository.getCartCount(email);
    }
    public Single<List<Product>> search(String search){
        return mainRepository.search(search);
    }

}
