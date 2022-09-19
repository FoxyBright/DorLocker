package com.example.forexample.Services.Retrofit.Response;

import com.example.forexample.Models.Camera;
import com.example.forexample.Models.Data;

import java.util.List;


public class CamerasResponse{

    private final Data data = null;

    public List<Camera> getCameras() {
        return data.getCameras();
    }
}