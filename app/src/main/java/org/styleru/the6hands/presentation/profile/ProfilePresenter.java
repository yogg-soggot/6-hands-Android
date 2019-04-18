package org.styleru.the6hands.presentation.profile;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.domain.interactors.UserInfoInteractor;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

    private final UserInfoInteractor userInfoInteractor;
    private Disposable disposable;
    private User user;

    @Inject
    ProfilePresenter(UserInfoInteractor userInfoInteractor) {
        this.userInfoInteractor = userInfoInteractor;
    }

    void onVkButtonClicked(){
        getViewState().openLink(String.format("http://vk.com/id%s", user.getId()));
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable = userInfoInteractor.getUserFromVk().subscribe(
                        user ->{
                            this.user = user;
                            getViewState().setUser(user);
                        },
                        e -> getViewState().showMessage(e.getMessage()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
