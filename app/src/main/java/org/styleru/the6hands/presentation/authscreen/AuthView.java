package org.styleru.the6hands.presentation.authscreen;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface AuthView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void vkAuth();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showMessage(String message);
}
