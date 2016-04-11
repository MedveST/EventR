package aut.bme.hu.eventr.interactor;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @Provides
    public EventInteractor getEventInteractor()
    {
        return new EventInteractor();
    }

    @Provides
    public UserInteractor getUserInteractor()
    {
        return new UserInteractor();
    }
}
