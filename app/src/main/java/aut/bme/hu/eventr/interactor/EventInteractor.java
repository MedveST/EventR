package aut.bme.hu.eventr.interactor;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.model.EventModel;

public class EventInteractor {
    @Inject
    EventModel model;

    public EventInteractor() {
        EventRApplication.injector.inject(this);
    }

    public String getString() {
        return model.getNextString();
    }
}
