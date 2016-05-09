package aut.bme.hu.eventr.repository.mock;

import aut.bme.hu.eventr.repository.Repository;
import dagger.Module;
import dagger.Provides;

@Module
public class MockRepositoryModule {

    @Provides
    public Repository getRepository()
    {
        return new MockRepository();
    }
}
