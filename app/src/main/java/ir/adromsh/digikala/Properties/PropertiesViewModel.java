package ir.adromsh.digikala.Properties;

import java.util.List;

import ir.adromsh.digikala.Model.Properties;
import io.reactivex.Single;

public class PropertiesViewModel {
    PropertiesRepository propertiesRepository=new PropertiesRepository();
    public Single<List<Properties>> getProperties(){
        return propertiesRepository.getProperties();
    }
}
