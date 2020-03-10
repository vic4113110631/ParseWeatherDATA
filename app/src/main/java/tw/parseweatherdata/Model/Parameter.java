package tw.parseweatherdata.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Parameter implements Serializable {
    @SerializedName("parameterName") private String mTemp;
    @SerializedName("parameterUnit") private String mUnit;

    public String getTemp() {
        return mTemp;
    }

    public void setTemp(String mTemp) {
        this.mTemp = mTemp;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String mUnit) {
        this.mUnit = mUnit;
    }
}
