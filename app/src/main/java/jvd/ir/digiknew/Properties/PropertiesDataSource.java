package jvd.ir.digiknew.Properties;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Model.Detail;
import jvd.ir.digiknew.Model.Product;

public interface PropertiesDataSource {

    Single<List<Detail>> getDetailss(String id);
}
