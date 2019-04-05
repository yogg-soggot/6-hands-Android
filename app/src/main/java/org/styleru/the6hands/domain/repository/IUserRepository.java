package org.styleru.the6hands.domain.repository;


import org.styleru.the6hands.domain.entities.User;

import io.reactivex.Maybe;
import io.reactivex.Single;

public interface IUserRepository {

    Maybe<User> getUserFromDB();
    Single<User> getUserFromVk();
}
