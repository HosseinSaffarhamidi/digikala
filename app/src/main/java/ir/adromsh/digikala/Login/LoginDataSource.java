package ir.adromsh.digikala.Login;

import io.reactivex.Single;

public interface LoginDataSource {
    Single<Message> login(String email,String pass);
}
