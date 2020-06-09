package jvd.ir.digiknew.Main;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.Banner;
import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.Model.Slider;

public class LocalMainDataSource implements MainDataSource {
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
        return null;
    }

    @Override
    public Single<Message> getBasketCount(String email) {
        return null;
    }
}
