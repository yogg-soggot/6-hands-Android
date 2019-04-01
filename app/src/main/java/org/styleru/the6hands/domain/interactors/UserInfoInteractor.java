package org.styleru.the6hands.domain.interactors;

import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.domain.repository.IUserRepository;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;

public class UserInfoInteractor {

    private IUserRepository userRepository;

    @Inject
    public UserInfoInteractor(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getUserFromDb(MaybeObserver<User> observer){
        userRepository.getUserFromDB(observer);
    }

    public void getUserFromVk(SingleObserver<User> observer){
        userRepository.getUserFromVk(observer);
    }

}
