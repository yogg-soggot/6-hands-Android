package org.styleru.the6hands.presentation.mainscreen;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.styleru.the6hands.R;
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

    //Place navigation here
    boolean onNavClicked(int id){
        switch (id) {
            case R.id.nav_list:
                /*getViewState().showMessage("Здесь пока ничего нет");*/
                //Temporary
                router.navigateTo(new Screens.ApartmentScreen());
                return true;
            case R.id.nav_map:
                getViewState().showMessage("Здесь пока ничего нет");
                return true;
            case R.id.nav_dialogs:
                getViewState().showMessage("Здесь пока ничего нет");
                return true;
            case R.id.nav_profile:
                router.newRootScreen(new Screens.ProfileScreen());
                return true;
        }
        return false;
    }
}
