package jvd.ir.digiknew.Detail;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Login.UserLoginInfo;
import jvd.ir.digiknew.Model.Detail;

public class DetailViewModel {

    DetailApiDataSource detailApiDataSource=new DetailApiDataSource();
    Context context;

    UserLoginInfo userLoginInfo;

    public DetailViewModel(Context context){
        this.context=context;
        userLoginInfo=new UserLoginInfo(context);
    }

    DetailRepository detailRepository=new DetailRepository();

    public Single<List<Detail>> getDetail(String id){
        return detailRepository.getDetail(id);
    }

    public String getUserEmail(){
        return userLoginInfo.getUserEmail();
    }

    public Single<Message> addToBasket(String productId,String email){
        return detailRepository.addToBasket(productId, email);
    }

    public Single<Message> getBasketCount(String email){
        return detailRepository.getBasketCount(email);
    }

}
