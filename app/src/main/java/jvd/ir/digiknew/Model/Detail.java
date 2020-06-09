
package jvd.ir.digiknew.Model;

import com.google.gson.annotations.SerializedName;


public class Detail {

    @SerializedName("color")
    private String mColor;
    @SerializedName("comments")
    private Comments mComments;
    @SerializedName("garantee")
    private String mGarantee;
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("introduction")
    private String mIntroduction;
    @SerializedName("price")
    private String mPrice;
    @SerializedName("rating")
    private String mRating;
    @SerializedName("title")
    private String mTitle;



    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public Comments getComments() {
        return mComments;
    }

    public void setComments(Comments comments) {
        mComments = comments;
    }

    public String getGarantee() {
        return mGarantee;
    }

    public void setGarantee(String garantee) {
        mGarantee = garantee;
    }

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
        mImage = image;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public void setIntroduction(String introduction) {
        mIntroduction = introduction;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
