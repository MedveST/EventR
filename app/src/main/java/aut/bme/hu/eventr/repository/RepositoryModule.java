package aut.bme.hu.eventr.repository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    public Repository getRepository()
    {
        return new SugarORMRepository();
    }
}
