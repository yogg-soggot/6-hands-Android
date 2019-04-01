package org.styleru.the6hands.domain.repository;


import org.styleru.the6hands.domain.entities.User;

import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;

public interface IUserRepository {

    void getUserFromDB(MaybeObserver<User> observer);
    void getUserFromVk(SingleObserver<User> observer);
}
