package jvd.ir.digiknew.Detail;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Login.UserLoginInfo;
import jvd.ir.digiknew.Model.Detail;

public class DetailRepository implements DetailDataSource {

    DetailApiDataSource detailApiDataSource=new DetailApiDataSource();

    @Override
    public Single<List<Detail>> getDetail(String id) {
        return detailApiDataSource.getDetail(id);
    }

    @Override
    public Single<Message> addToBasket(String productId, String email) {
        return detailApiDataSource.addToBasket(productId, email);
    }

    @Override
    public Single<Message> getBasketCount(String email) {
        return detailApiDataSource.getBasketCount(email);
    }


}
