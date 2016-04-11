package aut.bme.hu.eventr.model;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    @Provides
    public EventModel getEventModel()
    {
        return new EventModel();
    }

    @Provides
    public UserModel getUserModel()
    {
        return new UserModel();
    }
}
