package ir.adromsh.digikala.Login;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import ir.adromsh.digikala.Main.MainDataSource;
import ir.adromsh.digikala.Main.MyTimer;
import ir.adromsh.digikala.Model.Banner;
import ir.adromsh.digikala.Model.Cat;
import ir.adromsh.digikala.Model.Product;
import io.reactivex.Single;

public class UserLoginInfo implements MainDataSource {
    private SharedPreferences sharedPreferences;

    public UserLoginInfo(Context context) {
        sharedPreferences=context.getSharedPreferences("login",Context.MODE_PRIVATE);
    }

    public void saveLoginData(String email){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("email",email);
        editor.apply();
    }
    @Override
    public String getUserEmail() {
        return sharedPreferences.getString("email","");
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


}
