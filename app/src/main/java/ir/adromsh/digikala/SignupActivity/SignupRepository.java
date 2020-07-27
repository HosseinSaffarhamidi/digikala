package ir.adromsh.digikala.SignupActivity;

import ir.adromsh.digikala.Login.Message;
import io.reactivex.Single;

public class SignupRepository implements SignupDataSource {
    ApiSignup apiSignup=new ApiSignup();
    @Override
    public Single<Message> signup(String email, String pass) {
        return apiSignup.signup(email,pass);
    }
}
