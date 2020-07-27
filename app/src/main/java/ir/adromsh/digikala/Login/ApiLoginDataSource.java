package ir.adromsh.digikala.Login;

import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import io.reactivex.Single;

public class ApiLoginDataSource implements LoginDataSource {
    ApiService apiService= ApiProvider.apiProvider();
    @Override
    public Single<Message> login(String email,String pass) {
        return apiService.login(email,pass);
    }
}
