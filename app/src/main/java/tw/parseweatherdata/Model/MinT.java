package tw.parseweatherdata.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MinT implements Serializable {
    @SerializedName("startTime") private String mStartTime;
    @SerializedName("endTime")  private String mEndTime;
    @SerializedName("parameter") private Parameter mParameter;

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    public Parameter getParameter() {
        return mParameter;
    }

    public void setParameter(Parameter mParameter) {
        this.mParameter = mParameter;
    }

}
