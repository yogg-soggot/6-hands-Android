package org.styleru.the6hands;

import android.app.Application;

import org.styleru.the6hands.dagger.AppComponent;
import org.styleru.the6hands.dagger.DaggerAppComponent;

import io.realm.Realm;

public class SixHandsApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        Realm.init(getApplicationContext());
        appComponent = DaggerAppComponent.builder()
                .build();
        super.onCreate();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
