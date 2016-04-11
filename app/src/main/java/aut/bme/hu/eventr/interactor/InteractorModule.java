package aut.bme.hu.eventr.interactor;

import dagger.Module;
import dagger.Provides;

@Module
public class InteractorModule {
    @Provides
    public EventInteractor getStringInteractor() {
        return new EventInteractor();
    }
}
