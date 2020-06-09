
package jvd.ir.digiknew.Main;


import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class MyTimer {

    @SerializedName("hour")
    private int mHour;
    @SerializedName("min")
    private int mMin;
    @SerializedName("sec")
    private int mSec;

    public int getHour() {
        return mHour;
    }

    public void setHour(int hour) {
        mHour = hour;
    }

    public int getMin() {
        return mMin;
    }

    public void setMin(int min) {
        mMin = min;
    }

    public int getSec() {
        return mSec;
    }

    public void setSec(int sec) {
        mSec = sec;
    }

}
