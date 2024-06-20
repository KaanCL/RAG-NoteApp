package com.example.projecta.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.projecta.Util.Credentials.BASE_URL;

public class RetrofitRequest {

    private static Retrofit retrofit_Note;

    public static Retrofit getRetrofitInstance(){

        Gson gson = new GsonBuilder().setLenient().create();



            retrofit_Note = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();



         return retrofit_Note;

    }


}
