package org.styleru.the6hands.presentation.mainscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.styleru.the6hands.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Router router;

    @Inject
    MainPresenter(Router router) {
        this.router = router;
    }

    void onStart(){
        router.replaceScreen(new Screens.ProfileScreen());
    }
}
