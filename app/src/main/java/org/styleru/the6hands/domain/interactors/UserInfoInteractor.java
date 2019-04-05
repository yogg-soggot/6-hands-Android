package org.styleru.the6hands.domain.interactors;

import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.domain.repository.IUserRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class UserInfoInteractor {

    private IUserRepository userRepository;

    @Inject
    public UserInfoInteractor(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Maybe<User> getUserFromDb(){
        return userRepository.getUserFromDB();
    }

    public Single<User> getUserFromVk(){
        return userRepository.getUserFromVk();
    }

}
