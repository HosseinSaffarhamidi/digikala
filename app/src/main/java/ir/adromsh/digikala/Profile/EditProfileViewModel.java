package ir.adromsh.digikala.Profile;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Favorite;
import io.reactivex.Single;

public class EditProfileViewModel {
    EditProfileRepository repository = new EditProfileRepository();

    public Single<Message> updateProfile(String email, String name, String family
            , String code, String home, String mobile, String birthday, int jensiat, int khabarname, int level) {
        return repository.updateProfile(email, name, family, code, home, mobile, birthday, jensiat, khabarname, level);
    }

    public Single<List<Favorite>> getFavorites(String email) {
        return repository.getFavorites(email);
    }

    public Single<Message> deleteFav(String id) {
        return repository.deleteFav(id);
    }
}
