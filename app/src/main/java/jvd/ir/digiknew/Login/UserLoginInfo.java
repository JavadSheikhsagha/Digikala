package jvd.ir.digiknew.Login;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Main.MainDataSource;
import jvd.ir.digiknew.Main.MyTimer;
import jvd.ir.digiknew.Model.Banner;
import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.Model.Slider;

public class UserLoginInfo implements MainDataSource {

    private SharedPreferences sharedPreferences;

    public  UserLoginInfo(Context context){
        sharedPreferences=context.getSharedPreferences("login",Context.MODE_PRIVATE);
    }

    public void saveLoginData(String email){

        SharedPreferences.Editor editor1= sharedPreferences.edit();
        editor1.putString("email",email);
        editor1.apply();

    }

    @Override
    public Single<List<Product>> getProducts() {
        return null;
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return null;
    }

    @Override
    public Single<List<Slider>> getSlider() {
        return null;
    }

    @Override
    public Single<MyTimer> getTimer() {
        return null;
    }

    @Override
    public String getUserEmail() {
        return sharedPreferences.getString("email","");
    }

    @Override
    public Single<Message> getBasketCount(String email) {
        return null;
    }

}
