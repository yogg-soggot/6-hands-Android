package org.styleru.the6hands.data.repository;

import com.vk.api.sdk.VK;

import org.styleru.the6hands.data.api.SixHandsApi;
import org.styleru.the6hands.data.vkmethods.VkUserRequest;
import org.styleru.the6hands.domain.entities.Apartment;
import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.domain.repository.IUserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRepository implements IUserRepository {

    private SixHandsApi sixHandsApi;

    @Inject
    UserRepository(SixHandsApi sixHandsApi) {
        this.sixHandsApi = sixHandsApi;
    }

    @Override
    public Single<User> getUserFromVk() {
        return Single.fromCallable(() -> VK.executeSync(new VkUserRequest()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Maybe<List<Apartment>> getUserApartments(long userId) {
        return sixHandsApi.getUserApartments(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
