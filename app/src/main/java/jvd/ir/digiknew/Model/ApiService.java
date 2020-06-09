package jvd.ir.digiknew.Model;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Main.MyTimer;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("android/readamazing.php")
    Single<List<Product>> getProduct();

    @GET("android/readbanner.php")
    Single<List<Banner>> getBanners();

    @GET("android/readslider.php")
    Single<List<Slider>> getSliders();

    @GET("android/timer.php")
    Single<MyTimer> getTimer();

    @FormUrlEncoded
    @POST("android/login.php")
    Single<Message> login(@Field("email") String email,@Field("pass") String pass);

    @FormUrlEncoded
    @POST("android/signup.php")
    Single<Message> signUp(@Field("email") String email, @Field("pass")String pass);

    @GET("android/getdetail.php")
    Single<List<Detail>> getDetail(@Query("id")String id);

    @GET("android/comments.php")
    Single<List<Comments>> getComments(@Query("id")String id);

    @GET("android/like.php")
    Single<Message> likeComment(@Query("id") String id);

    @GET("android/dislike.php")
    Single<Message> dislikeComment(@Query("id") String id);

    @FormUrlEncoded
    @POST("android/editprofile.php")
    Single<List<EditProfile>> setProfile(@Field("email") String email,@Field("name") String name,@Field("code")String code,@Field("home")String home,@Field("mobile")String mobile,@Field("birthday")String birthday,@Field("sex")String sex,@Field("khabarname") String khabarname);

    @GET("android/basket.php")
    Single<Message> addToBasket(@Query("productid") String productId,
                                @Query("email")String  email);

    @GET("android/getbasketcount.php")
    Single<Message> getBasketCount (@Query("email")String email);

    @GET("android/basketlist.php")
    Single<List<BasketProduct>> getBasketInfo(@Query("email")String email);

    @GET("android/deletebasket.php")
    Single<Message> deleteBasket(@Query("basket_id")String basketId);



}
