package org.styleru.the6hands.presentation.profile;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ProfileView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setProfileName(String name);

    @StateStrategyType(SingleStateStrategy.class)
    void showMessage(String message);
}
