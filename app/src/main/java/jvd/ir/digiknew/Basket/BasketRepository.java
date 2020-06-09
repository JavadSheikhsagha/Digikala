package jvd.ir.digiknew.Basket;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.BasketProduct;

public class BasketRepository {

    BasketApiMainDataSource apiMainDataSource=new BasketApiMainDataSource();

    public Single<List<BasketProduct>> getBasketInfo(String email){
        return apiMainDataSource.getBasketInfo(email);
    }

    public Single<Message> deleteBasket(String basketId){
        return apiMainDataSource.deleteBasket(basketId);
    }
}
