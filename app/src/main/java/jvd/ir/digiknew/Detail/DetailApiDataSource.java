package jvd.ir.digiknew.Detail;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.ApiProvider;
import jvd.ir.digiknew.Model.ApiService;
import jvd.ir.digiknew.Model.Detail;

public class DetailApiDataSource implements DetailDataSource {

    ApiService apiService= ApiProvider.apiProvider();


    @Override
    public Single<List<Detail>> getDetail(String id) {
        return apiService.getDetail(id);
    }

    @Override
    public Single<Message> addToBasket(String productId, String email) {
        return apiService.addToBasket(productId, email);
    }

    @Override
    public Single<Message> getBasketCount(String email) {
        return apiService.getBasketCount(email);
    }


}
