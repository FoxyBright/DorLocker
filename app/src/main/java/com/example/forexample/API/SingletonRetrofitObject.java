package com.example.forexample.API;

import com.itkacher.okprofiler.BuildConfig;
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonRetrofitObject {
    public static final String BASE_URL = "http://cars.cprogroup.ru/";
    public static Retrofit mRetrofit;
    private static SingletonRetrofitObject mInstance;

    private SingletonRetrofitObject() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new OkHttpProfilerInterceptor());
        }
        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static SingletonRetrofitObject getInstance() {
        if (mInstance == null) {
            mInstance = new SingletonRetrofitObject();
        }
        return mInstance;
    }

    public API getJSONApi() {
        return mRetrofit.create(API.class);
    }
}