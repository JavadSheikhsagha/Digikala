package jvd.ir.digiknew.Detail;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.Detail;

public interface DetailDataSource {

    Single<List<Detail>> getDetail(String id);

    Single<Message> addToBasket(String productId,String email);

    Single<Message> getBasketCount(String email);

}
