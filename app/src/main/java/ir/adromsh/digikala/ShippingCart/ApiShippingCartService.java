package ir.adromsh.digikala.ShippingCart;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.Basket;
import io.reactivex.Single;

public class ApiShippingCartService {
    private ApiService apiService = ApiProvider.apiProvider();

    public Single<Message> getCartCaount(String email) {
        return apiService.getBasketCount(email);
    }

    public Single<List<Basket>> getBasketList(String email) {
        return apiService.getBasketList(email);
    }
    public Single<Message> deleteBasketItem(String basketId){
        return apiService.deleteBasketItem(basketId);
    }
    public Single<List<Basket>> updateBasket(String orderId,String email){
        return apiService.updateBasket(orderId, email);
    }
}
