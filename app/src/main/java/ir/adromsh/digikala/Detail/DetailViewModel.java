package ir.adromsh.digikala.Detail;


import android.content.Context;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Login.UserLoginInfo;
import ir.adromsh.digikala.Model.Detail;
import io.reactivex.Single;

public class DetailViewModel {
    Context context;
    UserLoginInfo userLoginInfo;

    public DetailViewModel(Context context) {
        this.context = context;
        userLoginInfo = new UserLoginInfo(context);
    }

    private DetailRepository detailRepository = new DetailRepository();

    public Single<List<Detail>> getDetails(String id, String user) {
        return detailRepository.getDetails(id, user);
    }

    public String getUserEmail() {
        return userLoginInfo.getUserEmail();
    }

    public Single<Message> addFavorite(String email, String id, int parent, String title) {
        return detailRepository.addFavorite(email, id, parent, title);
    }

    public Single<Message> addToBasket(String productId, String email) {
        return detailRepository.addToBasket(productId, email);
    }

    public Single<Message> getBasketCount(String email) {
        return detailRepository.getBasketCount(email);
    }
}
