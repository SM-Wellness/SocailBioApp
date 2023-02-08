package com.shoppi.smwellness;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class data_model {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("smoking")
    @Expose
    private String smoking;

    @SerializedName("alcohol")
    @Expose
    private String alcohol;
    @SerializedName("bigThree")
    @Expose
    private String bigThree;
    @SerializedName("bmi")
    @Expose
    private String bmi;
    @SerializedName("sugarLevel")
    @Expose
    private String sugarLevel;
    @SerializedName("bloodPressure")
    @Expose
    private String bloodPressure;

    @SerializedName("todayCalorie")
    @Expose
    private String todayCalorie;
    @SerializedName("todayWater")
    @Expose
    private String todayWater;

    @SerializedName("todaySteps")
    @Expose
    private String todaySteps;

    @SerializedName("liverLevels")
    @Expose
    private String liverLevels;

    public String getId(){
        return id;
    }

    public String getSmoking(){
        return smoking;
    }

    public String getAlcohol(){
        return alcohol;
    }

    public String getBigThree(){
        return bigThree;
    }

    public String getBmi(){
        return bmi;
    }
    public String getSugarLevel(){
        return sugarLevel;
    }

    public String getBloodPressure(){
        return bloodPressure;
    }
    public String getTodayCalorie(){
        return todayCalorie;
    }

    public String getTodayWater(){
        return todayWater;
    }
    public String getTodaySteps(){
        return todaySteps;
    }

    public String getLiverLevels(){
        return liverLevels;
    }
}
