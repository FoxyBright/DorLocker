package com.example.forexample;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class JSONCamerasData {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private CamerasData Camerasdata;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CamerasData getCamerasData() {
        return Camerasdata;
    }

    public void setCamerasData(CamerasData Camerasdata) {
        this.Camerasdata = Camerasdata;
    }

}