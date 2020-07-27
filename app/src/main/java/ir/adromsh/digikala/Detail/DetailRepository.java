package ir.adromsh.digikala.Detail;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Detail;
import io.reactivex.Single;

public class DetailRepository {
    private DetailApiService detailApiService = new DetailApiService();

    public Single<List<Detail>> getDetails(String id, String user) {
        return detailApiService.getDetails(id, user);
    }

    public Single<Message> addFavorite(String email, String id, int parent, String title) {
        return detailApiService.addFavorite(email, id, parent, title);
    }

    public Single<Message> addToBasket(String productId, String email) {
        return detailApiService.addToBasket(productId, email);
    }

    public Single<Message> getBasketCount(String email) {
        return detailApiService.getBasketCount(email);
    }
}
