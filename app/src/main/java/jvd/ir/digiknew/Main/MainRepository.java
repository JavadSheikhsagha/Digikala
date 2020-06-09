package jvd.ir.digiknew.Main;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Login.UserLoginInfo;
import jvd.ir.digiknew.Model.Banner;
import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.Model.Slider;

public class MainRepository implements MainDataSource {

    Context context;

    ApiMainDataSource apiMainDataSource= new ApiMainDataSource();
    UserLoginInfo userLoginInfo;

    public MainRepository(Context context){
        this.context=context;
        userLoginInfo=new UserLoginInfo(context);
    }



    @Override
    public Single<List<Product>> getProducts() {
        return apiMainDataSource.getProducts();
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return apiMainDataSource.getBanners();
    }

    public Single<List<Slider>> getSlider(){
        return apiMainDataSource.getSlider();
    }

    @Override
    public Single<MyTimer> getTimer() {
        return apiMainDataSource.getTimer();
    }

    @Override
    public String getUserEmail() {
        return userLoginInfo.getUserEmail();
    }

    @Override
    public Single<Message> getBasketCount(String email) {
        return apiMainDataSource.getBasketCount(email);
    }
}
