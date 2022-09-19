package com.example.forexample.Models;

import java.util.List;

public class Data {
    private final List<Camera> cameras = null;

    public List<Camera> getCameras() {
        for (Camera camera : cameras)
            if (camera.getRoom() == null)
                camera.setRoom("Вне комнат");
        return cameras;
    }
}
