package jvd.ir.digiknew.Properties;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Model.Detail;
import jvd.ir.digiknew.Model.Product;

public class PropertiesRepository implements PropertiesDataSource {

    PropertiesApiDataSource propertiesApiDataSource=new PropertiesApiDataSource();

    @Override
    public Single<List<Detail>> getDetailss(String id) {
        return propertiesApiDataSource.getDetailss(id);
    }
}
