package ir.adromsh.digikala.ShippingCart;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Basket;
import io.reactivex.Single;

public class ShippingRepository {
    ApiShippingCartService apiShippingCartService = new ApiShippingCartService();

    public Single<Message> getCartCaount(String email) {
        return apiShippingCartService.getCartCaount(email);
    }

    public Single<List<Basket>> getBasketList(String email) {
        return apiShippingCartService.getBasketList(email);
    }

    public Single<Message> deleteBasketItem(String basketId) {
        return apiShippingCartService.deleteBasketItem(basketId);
    }
    public Single<List<Basket>> updateBasket(String orderId,String email){
        return apiShippingCartService.updateBasket(orderId, email);
    }
}
