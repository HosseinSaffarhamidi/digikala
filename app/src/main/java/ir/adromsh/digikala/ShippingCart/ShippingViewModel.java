package ir.adromsh.digikala.ShippingCart;

import android.content.Context;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Login.UserLoginInfo;
import ir.adromsh.digikala.Model.Basket;
import io.reactivex.Single;

public class ShippingViewModel {
    public UserLoginInfo userLoginInfo;

    public ShippingViewModel(Context context) {
        userLoginInfo = new UserLoginInfo(context);
    }

    ShippingRepository repository = new ShippingRepository();

    public Single<Message> getCartCaount(String email) {
        return repository.getCartCaount(email);
    }

    public String getUserEmail() {
        return userLoginInfo.getUserEmail();
    }

    public Single<List<Basket>> getBasketList(String email) {
        return repository.getBasketList(email);
    }

    public Single<Message> deleteBasketItem(String basketId) {
        return repository.deleteBasketItem(basketId);
    }
    public Single<List<Basket>> updateBasket(String orderId,String email){
        return repository.updateBasket(orderId, email);
    }
}
