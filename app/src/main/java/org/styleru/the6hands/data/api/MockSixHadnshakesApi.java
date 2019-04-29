package org.styleru.the6hands.data.api;

import org.styleru.the6hands.domain.entities.Apartment;
import org.styleru.the6hands.domain.entities.Facility;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Maybe;

public class MockSixHadnshakesApi implements SixHandsApi {
    @Override
    public Maybe<List<Apartment>> getUserApartments(long userId) {
        Facility facility = new Facility();
        facility.setName("Kubik");
        facility.setIcon("https://banner2.kisspng.com/20180422/ozw/kisspng-computer-icons-material-design-hamburger-button-yo-5adc8d281823b1.1433811215244034960989.jpg");
        Apartment a1 = new Apartment(), a2 = new Apartment(), a3 = new Apartment();
        a1.setAddress("Ad1");
        a1.setFacilities(Arrays.asList(facility, facility));
        a2.setAddress("a2");
        a3.setFacilities(Collections.emptyList());
        a2.setFacilities(Arrays.asList(facility, facility));
        return Maybe.just(Arrays.asList(a1, a2, a3));
    }
}
