package com.example.forexample.Services.Responces;

import com.example.forexample.Classes.Camera;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CamerasResponse {

    @SerializedName("data")
    @Expose
    private final Cameras cameras = null;

    public List<Camera> getCameras() {
        return cameras.getCamera();
    }

    private class Cameras {
        private final List<Camera> cameras = null;

        public List<Camera> getCamera() {
            for (Camera camera : cameras)
                if (camera.getRoom() == null)
                    camera.setRoom("Вне комнат");
            return cameras;
        }
    }

}
