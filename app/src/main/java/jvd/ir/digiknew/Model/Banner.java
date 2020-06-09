package jvd.ir.digiknew.Model;

import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("id")
    private String mId;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("type")
    private String mType;



    public String getmId() {
        return mId;
    }

    public void setmId(String Id) {
        mId = Id;
    }

    public String getmUrlBanner() {
        return mUrl;
    }

    public void setmUrl(String Url) { mUrl = Url; }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }


}
