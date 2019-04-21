package org.styleru.the6hands.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.styleru.the6hands.data.api.SixHandsApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    SixHandsApi provideSixHandsApi(Gson gson){
        return new Retrofit.Builder()
                .baseUrl(SixHandsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(SixHandsApi.class);
    }

    @Provides
    Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }
}
