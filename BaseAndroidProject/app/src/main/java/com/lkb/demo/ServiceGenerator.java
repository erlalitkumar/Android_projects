package com.lkb.demo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ServiceGenerator {
    public static FileUploadService createService(Class<?> c){
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

      Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.106:4200/")
                .addConverterFactory(GsonConverterFactory.create())
                //.client(client)
                .build();
      return (FileUploadService) retrofit.create(c);
    }

}
