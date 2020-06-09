package jvd.ir.digiknew.Basket;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Login.UserLoginInfo;
import jvd.ir.digiknew.Model.BasketProduct;

public class BasketViewModel {

    BasketRepository repository=new BasketRepository();

    UserLoginInfo userLoginInfo;


    public BasketViewModel (Context context){
        userLoginInfo=new UserLoginInfo(context);
    }

    public Single<List<BasketProduct>> getBasketInfo(String email){
        return repository.getBasketInfo(email);
    }

    public String getUserEmail(){
        return userLoginInfo.getUserEmail();
    }

    public Single<Message> deleteBasket(String basketId){
        return repository.deleteBasket(basketId);
    }
}
