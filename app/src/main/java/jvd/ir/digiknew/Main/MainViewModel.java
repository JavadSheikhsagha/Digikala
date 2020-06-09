package jvd.ir.digiknew.Main;

import android.content.Context;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Login.UserLoginInfo;
import jvd.ir.digiknew.Model.Banner;
import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.Model.Slider;

public class MainViewModel  {

    private Context context;

    UserLoginInfo userLoginInfo;

    MainRepository mainRepository;

    public MainViewModel(Context context){
        this.context=context;
        userLoginInfo=new UserLoginInfo(context);
        mainRepository=new MainRepository(context);
    }


    public Single<List<Product>> getProducts(){
        return mainRepository.getProducts();
    }

    public Single<List<Banner>> getBanners(){
        return mainRepository.getBanners();
    }

    public Single<List<Slider>> getSlider(){
        return mainRepository.getSlider();
    }

    public Single<MyTimer> getTimer(){
        return mainRepository.getTimer();
    }

    public void saveEmailData(String email){
        userLoginInfo.saveLoginData(email);
    }

    public String getEmailData(){
        return mainRepository.getUserEmail();
    }

    public Single<Message> getBasketCount(String email){
        return mainRepository.getBasketCount(email);
    }

}
