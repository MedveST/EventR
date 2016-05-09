package aut.bme.hu.eventr.repository;

import aut.bme.hu.eventr.BuildConfig;

public class RepositoryModule {
    public Repository getRepository()
    {
        if (BuildConfig.IS_MOCK)
        {
            return new MockRepository();
        }
        else
        {
            return new SugarORMRepository();
        }
    }
}
