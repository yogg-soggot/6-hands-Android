package org.styleru.the6hands.presentation.authscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;

import org.styleru.the6hands.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {

    private Router router;

    @Inject
    AuthPresenter(Router router) {
        this.router = router;
    }

    @Override
    protected void onFirstViewAttach() {
        if (VK.isLoggedIn()) {
            router.newRootScreen(new Screens.MainScreen());
        }
    }

    void onClickVkAuth(){
        getViewState().vkAuth();
    }

    void onLogin(VKAccessToken token){
        router.newRootScreen(new Screens.MainScreen());
    }

    void onLoginFailed(){
        getViewState().showMessage("Error while logging");
    }
}
