package org.eldorado.simpleweather.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteModule {

    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public Retrofit provideRetrofit(String apiUrl) {
        return new Retrofit.Builder()
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(apiUrl)
                .build();
    }

    public OpenWeatherService provideOpenWeatherService() {
        return provideRetrofit(RemoteContract.BASE_API_LAYER).create(OpenWeatherService.class);
    }

}
