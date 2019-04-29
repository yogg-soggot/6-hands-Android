package org.styleru.the6hands.presentation.apartmentscreen;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ApartmentView extends MvpView {

    @StateStrategyType(SingleStateStrategy.class)
    void showMessage(String message);



}
