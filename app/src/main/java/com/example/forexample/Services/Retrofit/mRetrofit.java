package com.example.forexample.Services.Retrofit;

import com.itkacher.okprofiler.BuildConfig;
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mRetrofit {
    public static final String BASE_URL = "http://cars.cprogroup.ru/";
    public static Retrofit mRetrofit;
    private static com.example.forexample.Services.Retrofit.mRetrofit mInstance;

    private mRetrofit() {
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

    public static com.example.forexample.Services.Retrofit.mRetrofit getInstance() {
        if (mInstance == null) {
            mInstance = new mRetrofit();
        }
        return mInstance;
    }

    public API getAPI() {
        return mRetrofit.create(API.class);
    }
}