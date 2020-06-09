
package jvd.ir.digiknew.Model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Comments {

    @SerializedName("dislikes")
    private String mDislikes;
    @SerializedName("id")
    private String mId;
    @SerializedName("idproduct")
    private String mIdproduct;
    @SerializedName("likes")
    private String mLikes;
    @SerializedName("matn")
    private String mMatn;
    @SerializedName("user")
    private String mUser;

    public String getDislikes() {
        return mDislikes;
    }

    public void setDislikes(String dislikes) {
        mDislikes = dislikes;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getIdproduct() {
        return mIdproduct;
    }

    public void setIdproduct(String idproduct) {
        mIdproduct = idproduct;
    }

    public String getLikes() {
        return mLikes;
    }

    public void setLikes(String likes) {
        mLikes = likes;
    }

    public String getMatn() {
        return mMatn;
    }

    public void setMatn(String matn) {
        mMatn = matn;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

}
