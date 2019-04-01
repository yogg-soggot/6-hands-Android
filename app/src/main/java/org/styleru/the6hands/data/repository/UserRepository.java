package org.styleru.the6hands.data.repository;

import android.util.Log;

import com.vk.api.sdk.VK;

import org.styleru.the6hands.data.vkmethods.VkUserRequest;
import org.styleru.the6hands.domain.entities.User;
import org.styleru.the6hands.domain.repository.IUserRepository;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

public class UserRepository implements IUserRepository {


    @Inject
    UserRepository() {

    }

    @Override
    public void getUserFromDB(MaybeObserver<User> observer) {
        Realm realm = Realm.getDefaultInstance();
        Log.e("DB test", "" + realm.where(User.class).findAll().size());
        realm.where(User.class)
                .findFirstAsync()
                .<User>asFlowable()
                .firstElement()
                .map(user -> {
                    user.load();
                    return user;
                })
                .map(realm::copyFromRealm)
                .subscribe(observer);
        realm.close();
    }

    @Override
    public void getUserFromVk(SingleObserver<User> observer) {
        Single.fromCallable(() -> VK.executeSync(new VkUserRequest()))
                .subscribeOn(Schedulers.io())
                .map(user -> {
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.copyToRealm(user);
                    realm.commitTransaction();
                    realm.close();
                    return user;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
