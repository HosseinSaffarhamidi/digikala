package ir.adromsh.digikala.Properties;

import java.util.List;

import ir.adromsh.digikala.Model.Properties;
import io.reactivex.Single;

public interface PropertiesDataSource {
    Single<List<Properties>> getProperties();
}
