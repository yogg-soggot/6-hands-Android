package org.styleru.the6hands.domain.interactors;

import org.styleru.the6hands.domain.entities.Apartment;
import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.domain.repository.IUserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class UserInfoInteractor {

    private IUserRepository userRepository;

    @Inject
    UserInfoInteractor(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<User> getUserFromVk(){
        return userRepository.getUserFromVk();
    }
    public Maybe<List<Apartment>> getUserApartments(long userId){
        return userRepository.getUserApartments(userId);
    }
}
