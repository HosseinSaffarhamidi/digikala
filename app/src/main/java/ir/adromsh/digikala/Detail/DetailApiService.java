package ir.adromsh.digikala.Detail;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.Detail;
import io.reactivex.Single;

public class DetailApiService {
    private ApiService apiService = ApiProvider.apiProvider();

    public Single<List<Detail>> getDetails(String id, String user) {
        return apiService.getDetails(id, user);
    }

    public Single<Message> addFavorite(String email, String id, int parent, String title) {
        return apiService.addFavorite(email, id, parent, title);
    }

    public Single<Message> addToBasket(String productId,String email){
        return apiService.addToBasket(productId, email);
    }
    public Single<Message> getBasketCount(String email){
        return apiService.getBasketCount(email);
    }
}
