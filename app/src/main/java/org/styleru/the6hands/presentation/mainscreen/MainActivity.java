package org.styleru.the6hands.presentation.mainscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import org.styleru.the6hands.R;
import org.styleru.the6hands.Screens;
import org.styleru.the6hands.SixHandsApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;


public class MainActivity extends MvpAppCompatActivity implements MainView {

    @Inject
    @InjectPresenter
    MainPresenter mainPresenter;

    @Inject
    NavigatorHolder navigatorHolder;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private Navigator navigator = new SupportAppNavigator(this, R.id.main_container);


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return mainPresenter.onNavClicked(item.getItemId());
        }
    };

    @ProvidePresenter
    MainPresenter provideMainPresenter(){
        return mainPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SixHandsApplication.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter.onStart(getIntent().getExtras().getParcelable(Screens.ProfileScreen.USER_KEY));
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    //Needed to highlight bottom navigation item
    //Use this method any time you change screen without clicking on bottom navigation bar
    @Override
    public void setChecked(int id){
        bottomNavigationView.getMenu().findItem(id).setChecked(true);
    }
}
