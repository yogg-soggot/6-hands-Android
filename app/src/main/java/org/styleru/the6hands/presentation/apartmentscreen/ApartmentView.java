package org.styleru.the6hands.presentation.apartmentscreen;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface ApartmentView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void setApartmentDescription(String description);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setFacilities(boolean fridge, boolean pets, boolean wifi, boolean parking, boolean conditioning);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setSubwayLine(int subwayLine);

}
