package com.mbarra.crudappbio;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import okhttp3.OkHttpClient;

public class CRUDAppBio extends Application {
//    extend Application buat aplikasi pertama dijalanin

    public static String BASE_URL = "http://192.168.70.115/api_rpla/index.php/Api/";


    @Override
    public void onCreate() {
        super.onCreate();
//        http client yang bisa melakukan method yang ada di API
//        HttpLoggingInterceptor untuk request dan responsenya nanti kelihatan di LogCat
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("IDN",message);
            }
//            Level.Body untuk membaca Body request
        }).setLevel(HttpLoggingInterceptor.Level.BODY)).build();
        AndroidNetworking.initialize(this,okHttpClient);
    }
}
