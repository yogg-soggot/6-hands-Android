package org.styleru.the6hands;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import org.parceler.Parcels;
import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.presentation.authscreen.AuthActivity;
import org.styleru.the6hands.presentation.mainscreen.MainActivity;
import org.styleru.the6hands.presentation.profile.ProfileFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static final class AuthScreen extends SupportAppScreen {
        @Override
        public Intent getActivityIntent(Context context) {
            return new Intent(context, AuthActivity.class);
        }
    }

    public static final class MainScreen extends SupportAppScreen {

        private User user;

        public MainScreen(User user) {
            this.user = user;
        }

        @Override
        public Intent getActivityIntent(Context context) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(ProfileScreen.USER_KEY, Parcels.wrap(User.class, user));
            return intent;
        }
    }

    public static final class ProfileScreen extends SupportAppScreen {

        private Parcelable user;
        public final static String USER_KEY = "user";

        public ProfileScreen(Parcelable user) {
            this.user = user;
        }

        @Override
        public Fragment getFragment() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(USER_KEY, user);
            ProfileFragment profileFragment = new ProfileFragment();
            profileFragment.setArguments(bundle);
            return profileFragment;
        }
    }
}
