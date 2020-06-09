
package jvd.ir.digiknew.Model;


import android.util.Log;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Product {

    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("preprice")
    private String mPreprice;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("title")
    private String mTitle;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image+"";
    }

    public String getPPrice() {
        return mPreprice+"";
    }

    public void setPPrice(String preprice) {
        mPreprice = preprice+"";
    }

    public String getPrice() {
        return mPrice+"";
    }

    public void setPrice(String price) {
        mPrice = price+"";
    }

    public String getTitle() {
        return mTitle+"";
    }

    public void setTitle(String title) {
        mTitle = title+"";
    }




}
