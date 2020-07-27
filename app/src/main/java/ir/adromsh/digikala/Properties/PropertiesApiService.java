package ir.adromsh.digikala.Properties;

import java.util.List;

import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.Properties;
import io.reactivex.Single;

public class PropertiesApiService implements PropertiesDataSource {
    private ApiService apiService= ApiProvider.apiProvider();
    @Override
    public Single<List<Properties>> getProperties() {
        return apiService.getProperties();
    }
}
