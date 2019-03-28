package org.styleru.the6hands.presentation.profile;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

    @Inject
    ProfilePresenter() {

    }

    void onStart(){
        getViewState().setProfileName("Name");
    }
}
