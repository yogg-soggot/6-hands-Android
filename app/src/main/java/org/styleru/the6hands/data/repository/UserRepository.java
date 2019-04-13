package org.styleru.the6hands.data.repository;

import com.vk.api.sdk.VK;

import org.styleru.the6hands.data.vkmethods.VkUserRequest;
import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.domain.repository.IUserRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRepository implements IUserRepository {


    @Inject
    UserRepository() {}

    @Override
    public Single<User> getUserFromVk() {
       return Single.fromCallable(() -> VK.executeSync(new VkUserRequest()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
