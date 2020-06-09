
package jvd.ir.digiknew.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class BasketProduct {

    @SerializedName("basket_id")
    private String mBasketId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("productid")
    private String mProductid;
    @SerializedName("title")
    private String mTitle;

    public String getBasketId() {
        return mBasketId;
    }

    public void setBasketId(String basketId) {
        mBasketId = basketId;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getProductid() {
        return mProductid;
    }

    public void setProductid(String productid) {
        mProductid = productid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
