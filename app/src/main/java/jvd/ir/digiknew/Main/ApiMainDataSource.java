package jvd.ir.digiknew.Main;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.ApiProvider;
import jvd.ir.digiknew.Model.ApiService;
import jvd.ir.digiknew.Model.Banner;
import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.Model.Slider;

public class ApiMainDataSource implements MainDataSource {

    ApiService apiService=ApiProvider.apiProvider();

    @Override
    public Single<List<Product>> getProducts() {
        return apiService.getProduct();
    }

    @Override
    public Single<List<Banner>> getBanners() {
        return apiService.getBanners();
    }

    public Single<List<Slider>> getSlider(){
        return apiService.getSliders();
    }

    @Override
    public Single<MyTimer> getTimer() {
        return apiService.getTimer();
    }

    @Override
    public String getUserEmail() {
        return null;
    }

    @Override
    public Single<Message> getBasketCount(String email) {
        return apiService.getBasketCount(email);
    }

}
