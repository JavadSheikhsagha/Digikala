package jvd.ir.digiknew.Properties;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Model.ApiProvider;
import jvd.ir.digiknew.Model.ApiService;
import jvd.ir.digiknew.Model.Detail;
import jvd.ir.digiknew.Model.Product;

public class PropertiesApiDataSource implements PropertiesDataSource {

    ApiService apiService=ApiProvider.apiProvider();

    @Override
    public Single<List<Detail>> getDetailss(String id) {
        return apiService.getDetail(id);
    }
}
