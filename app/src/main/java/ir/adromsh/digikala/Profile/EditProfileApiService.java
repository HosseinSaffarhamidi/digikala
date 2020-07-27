package ir.adromsh.digikala.Profile;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.Favorite;
import io.reactivex.Single;

public class EditProfileApiService {
    ApiService apiService = ApiProvider.apiProvider();

    public Single<Message> updateProfile(String email, String name, String family
            , String code, String home, String mobile, String birthday, int jensiat, int khabarname, int level) {

        return apiService.updateProfile(email, name, family, code, home, mobile, birthday, jensiat, khabarname, level);
    }

    public Single<List<Favorite>> getFavorites(String email){
        return apiService.getFavorites(email);
    }
    public Single<Message> deleteFav(String id){
        return apiService.deleteFav(id);
    }
}
