package org.styleru.the6hands.presentation.authscreen;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vk.api.sdk.auth.VKAccessToken;

import org.styleru.the6hands.Screens;
import org.styleru.the6hands.domain.interactors.UserInfoInteractor;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class AuthPresenter extends MvpPresenter<AuthView> {

    private Router router;
    private UserInfoInteractor userInfoInteractor;
    private CompositeDisposable disposables;

    @Inject
    AuthPresenter(Router router, UserInfoInteractor userInfoInteractor) {
        this.router = router;
        this.userInfoInteractor = userInfoInteractor;
        disposables = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        Disposable disposable = userInfoInteractor.getUserFromDb()
                .subscribe(
                        user -> router.newRootScreen(new Screens.MainScreen(user)),
                        e -> {
                            Log.e("DB Error", e.getMessage());
                            getViewState().showButton();
                        },
                        getViewState()::showButton);
        disposables.add(disposable);
    }

    void onClickVkAuth(){
        getViewState().vkAuth();
    }

    void onLogin(VKAccessToken token){
        getViewState().hideButton();
        Disposable disposable = userInfoInteractor.getUserFromVk()
                .subscribe(
                        user -> router.newRootScreen(new Screens.MainScreen(user)),
                        e ->{
                            getViewState().showButton();
                            getViewState().showMessage("Error while loading user");
                            Log.e("VK Error", Log.getStackTraceString(e));
                        });
        disposables.add(disposable);
    }

    void onLoginFailed(){
        getViewState().showMessage("Error while logging");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }
}
