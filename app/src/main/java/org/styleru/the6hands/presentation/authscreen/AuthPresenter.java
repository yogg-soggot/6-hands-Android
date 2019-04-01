package org.styleru.the6hands.presentation.authscreen;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vk.api.sdk.auth.VKAccessToken;

import org.styleru.the6hands.Screens;
import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.domain.interactors.UserInfoInteractor;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {

    private Router router;
    private UserInfoInteractor userInfoInteractor;

    @Inject
    AuthPresenter(Router router, UserInfoInteractor userInfoInteractor) {
        this.router = router;
        this.userInfoInteractor = userInfoInteractor;
    }

    @Override
    protected void onFirstViewAttach() {
        userInfoInteractor.getUserFromDb(new MaybeObserver<User>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(User user) {
                router.newRootScreen(new Screens.MainScreen(user));
            }

            @Override
            public void onError(Throwable e) {
                Log.e("DB Error", e.getMessage());
                getViewState().showButton();
            }

            @Override
            public void onComplete() {
                getViewState().showButton();
            }
        });
    }

    void onClickVkAuth(){
        getViewState().vkAuth();
    }

    void onLogin(VKAccessToken token){
        getViewState().hideButton();
        userInfoInteractor.getUserFromVk(new SingleObserver<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(User user) {
                router.newRootScreen(new Screens.MainScreen(user));
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showMessage("Error while loading user");
                Log.e("VK Error", Log.getStackTraceString(e));
            }
        });

    }

    void onLoginFailed(){
        getViewState().showMessage("Error while logging");
    }

}
