package org.styleru.the6hands.presentation.apartmentscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.styleru.the6hands.Screens;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class ApartmentPresenter extends MvpPresenter<ApartmentView> {
    private Router router;

    @Inject
    ApartmentPresenter(Router router) {
        this.router = router;
    }

    void onBackPressed(){
        router.backTo(new Screens.ProfileScreen());
    }

}

