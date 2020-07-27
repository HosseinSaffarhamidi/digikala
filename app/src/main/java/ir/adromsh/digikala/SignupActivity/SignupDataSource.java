package ir.adromsh.digikala.SignupActivity;

import ir.adromsh.digikala.Login.Message;
import io.reactivex.Single;

public interface SignupDataSource {
    Single<Message> signup(String email, String pass);
}
