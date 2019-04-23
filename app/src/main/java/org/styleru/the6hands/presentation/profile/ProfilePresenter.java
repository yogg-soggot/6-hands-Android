package org.styleru.the6hands.presentation.profile;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.styleru.the6hands.Screens;
import org.styleru.the6hands.domain.entities.Apartment;
import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.domain.interactors.UserInfoInteractor;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

    private final UserInfoInteractor userInfoInteractor;
    private CompositeDisposable compositeDisposable;
    private User user;
    private Router router;

    @Inject
    ProfilePresenter(UserInfoInteractor userInfoInteractor, Router router) {
        this.userInfoInteractor = userInfoInteractor;
        this.router = router;
        compositeDisposable = new CompositeDisposable();
    }

    void onVkButtonClicked(){
        getViewState().openLink(String.format("http://vk.com/id%s", user.getId()));
    }

    void onMyApartmentClicked(Apartment apartment){
        router.navigateTo(new Screens.ApartmentScreen());
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        if (!Screens.isAnonUser()) {
            Disposable disposable = userInfoInteractor.getUserFromVk().subscribe(
                            user ->{
                                this.user = user;
                                getViewState().setUser(user);
                                loadApartments();
                            },
                            e -> getViewState().showMessage(e.getMessage()));
            compositeDisposable.add(disposable);
        }
    }

    private void loadApartments(){
        Disposable disposable = userInfoInteractor.getUserApartments(user.getId())
                .subscribe(apartments -> getViewState().setApartments(apartments),
                        error -> getViewState().showMessage(error.getMessage()));
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
