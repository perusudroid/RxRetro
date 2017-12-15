package com.perusudroid.sampleretro.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by perusu on 13/5/17.
 */

public class ApiClient {

    private final ApiInterface apiInterface;
    private static Retrofit retrofit = null;

    private static String BASE_URL = "http://192.168.43.248/RoomApi/v1/";

    public ApiClient(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }


    public static ApiInterface getInterface() {
        return getClient().create(ApiInterface.class);
    }

    private static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(getHttpLoggingInterceptor().build())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

        }
        return retrofit;
    }

    private static OkHttpClient.Builder getHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder();

    }


}
