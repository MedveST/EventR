package aut.bme.hu.eventr.interactor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @Provides
    @Singleton
    public EventInteractor getEventInteractor()
    {
        return new EventInteractor();
    }

    @Provides
    @Singleton
    public UserInteractor getUserInteractor()
    {
        return new UserInteractor();
    }
}
