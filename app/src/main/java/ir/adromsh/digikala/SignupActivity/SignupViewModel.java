package ir.adromsh.digikala.SignupActivity;

import android.content.Context;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Login.UserLoginInfo;
import io.reactivex.Single;

public class SignupViewModel {
    UserLoginInfo userLoginInfo;
    Context context;

    public SignupViewModel(Context context) {
        this.context = context;
        userLoginInfo = new UserLoginInfo(context);
    }

    SignupRepository signupRepository = new SignupRepository();

    public Single<Message> signup(String email, String pass) {
        return signupRepository.signup(email, pass);
    }

    public void saveUserInfo(String email){
        userLoginInfo.saveLoginData(email);
    }
}
