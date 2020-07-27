package ir.adromsh.digikala.SignupActivity;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import io.reactivex.Single;

public class ApiSignup implements SignupDataSource {
    ApiService apiService= ApiProvider.apiProvider();
    @Override
    public Single<Message> signup(String email, String pass) {
        return apiService.signup(email,pass);
    }
}
