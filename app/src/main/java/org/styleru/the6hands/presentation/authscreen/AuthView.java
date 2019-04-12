package org.styleru.the6hands.presentation.authscreen;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface AuthView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void vkAuth();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(String message);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setLoadingVisibility(Boolean visible);
}
