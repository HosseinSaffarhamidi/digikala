package ir.adromsh.digikala.Properties;

import java.util.List;

import ir.adromsh.digikala.Model.Properties;
import io.reactivex.Single;

public class PropertiesRepository implements PropertiesDataSource {
    PropertiesApiService propertiesApiService=new PropertiesApiService();
    @Override
    public Single<List<Properties>> getProperties() {
        return propertiesApiService.getProperties();
    }
}
