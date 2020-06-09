package jvd.ir.digiknew.Model;

import com.google.gson.annotations.SerializedName;

public class Slider {

    @SerializedName("id")
    private String mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("url")
    private String mUrl;



    public String getmId() {
        return mId;
    }

    public void setmId(String Id) {
        mId = Id;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String Title) {
        mTitle = Title;
    }

    public String getmUrlSlider() {
        return mUrl;
    }

    public void setmUrl(String Url) {
        mUrl = Url;
    }

}
