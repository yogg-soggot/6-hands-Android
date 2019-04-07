package org.styleru.the6hands.presentation.authscreen;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.api.sdk.auth.VKAuthCallback;

import org.jetbrains.annotations.NotNull;
import org.styleru.the6hands.R;
import org.styleru.the6hands.SixHandsApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;


public class AuthActivity extends MvpAppCompatActivity implements AuthView {

    @Inject
    @InjectPresenter
    AuthPresenter authPresenter;

    @Inject
    NavigatorHolder navigatorHolder;

    @BindView(R.id.vk_auth)
    TextView vkAuth;

    @BindView(R.id.auth_loader)
    ProgressBar progressBar;

    private Navigator navigator = new SupportAppNavigator(this, -1);

    @ProvidePresenter
    AuthPresenter provideAuthPresenter(){
        return authPresenter;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SixHandsApplication.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        VKAuthCallback callback = new VKAuthCallback() {
            @Override
            public void onLogin(@NotNull VKAccessToken vkAccessToken) {
                authPresenter.onLogin(vkAccessToken);
            }

            @Override
            public void onLoginFailed(int i) {
                authPresenter.onLoginFailed();
            }
        };

        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void vkAuth() {
        VK.login(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showButton() {
        progressBar.setVisibility(View.INVISIBLE);
        vkAuth.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButton() {
        vkAuth.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.vk_auth)
    void onClickVkAuth(){
        authPresenter.onClickVkAuth();
    }
}
