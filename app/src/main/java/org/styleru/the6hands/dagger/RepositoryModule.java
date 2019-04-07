package org.styleru.the6hands.dagger;

import org.styleru.the6hands.data.repository.UserRepository;
import org.styleru.the6hands.domain.repository.IUserRepository;

import dagger.Binds;
import dagger.Module;

@Module
abstract class RepositoryModule {

    @Binds
    abstract IUserRepository bindIUserRepository(UserRepository userRepository);
}
