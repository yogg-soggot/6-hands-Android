package org.styleru.the6hands.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.styleru.the6hands.data.api.MockSixHadnshakesApi;
import org.styleru.the6hands.data.api.SixHandsApi;

import dagger.Module;
import dagger.Provides;

@Module
class NetworkModule {

    @Provides
    SixHandsApi provideSixHandsApi(Gson gson){
//        return new Retrofit.Builder()
//                .baseUrl(SixHandsApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//                .create(SixHandsApi.class);
        return new MockSixHadnshakesApi();
    }

    @Provides
    Gson provideGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }
}
