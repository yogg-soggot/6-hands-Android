package org.styleru.the6hands.domain.repository;


import org.styleru.the6hands.domain.entities.Apartment;
import org.styleru.the6hands.domain.entities.User;

import java.util.List;

import io.reactivex.Single;

public interface IUserRepository {

    Single<User> getUserFromVk();
    Single<List<Apartment>> getUserApartments(long userId);
}
