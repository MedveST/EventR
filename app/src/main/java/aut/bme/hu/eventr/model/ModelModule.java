package aut.bme.hu.eventr.model;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    @Provides
    public EventModel getStringModel() {
        return new EventModel();
    }
}
