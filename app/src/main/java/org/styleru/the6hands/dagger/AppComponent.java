package org.styleru.the6hands.dagger;

import android.content.Context;

import org.styleru.the6hands.presentation.authscreen.AuthActivity;
import org.styleru.the6hands.presentation.mainscreen.MainActivity;
import org.styleru.the6hands.presentation.profile.ProfileFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {NavigationModule.class})
public interface AppComponent {

    void inject(AuthActivity authActivity);
    void inject(MainActivity mainActivity);
    void inject(ProfileFragment profileFragment);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder context(Context context);
    }
}
