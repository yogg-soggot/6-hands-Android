package org.styleru.the6hands.presentation.mainscreen;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showMessage(String message);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setChecked(int id);
}
