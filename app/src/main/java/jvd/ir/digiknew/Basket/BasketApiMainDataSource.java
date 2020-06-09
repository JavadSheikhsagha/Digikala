package jvd.ir.digiknew.Basket;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.ApiProvider;
import jvd.ir.digiknew.Model.ApiService;
import jvd.ir.digiknew.Model.BasketProduct;

public class BasketApiMainDataSource {

    ApiService apiService= ApiProvider.apiProvider();

    public Single<List<BasketProduct>> getBasketInfo(String email){
        return apiService.getBasketInfo(email);
    }

    public Single<Message> deleteBasket(String basketId){
        return apiService.deleteBasket(basketId);
    }
}
