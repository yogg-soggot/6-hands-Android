package org.styleru.the6hands.presentation.profile;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.styleru.the6hands.domain.interactors.UserInfoInteractor;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

    private final UserInfoInteractor userInfoInteractor;

    @Inject
    ProfilePresenter(UserInfoInteractor userInfoInteractor) {
        this.userInfoInteractor = userInfoInteractor;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Disposable disposable = userInfoInteractor.getUserFromVk().subscribe(
                        user -> getViewState().setUser(user),
                        e -> getViewState().showMessage(e.getMessage()));
    }
}
