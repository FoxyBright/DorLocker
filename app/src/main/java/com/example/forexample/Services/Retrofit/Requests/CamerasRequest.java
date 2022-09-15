package com.example.forexample.Services.Retrofit.Requests;

import com.example.forexample.Models.Camera;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CamerasRequest implements Request {

    @SerializedName("data")
    @Expose
    private final Cameras cameras = null;

    @Override
    public List<Camera> getData() {
        return cameras.getCamera();
    }

    private static class Cameras {
        private final List<Camera> cameras = null;
        public List<Camera> getCamera() {
            for (Camera camera : cameras)
                if (camera.getRoom() == null)
                    camera.setRoom("Вне комнат");
            return cameras;
        }
    }
}
