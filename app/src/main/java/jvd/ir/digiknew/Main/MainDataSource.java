package jvd.ir.digiknew.Main;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.Banner;
import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.Model.Slider;

public interface MainDataSource {

    Single<List<Product>> getProducts();

    Single<List<Banner>> getBanners();

    Single<List<Slider>> getSlider();

    Single<MyTimer> getTimer();

    String getUserEmail();

    Single<Message> getBasketCount(String email);
}
