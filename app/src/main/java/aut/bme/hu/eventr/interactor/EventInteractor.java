package aut.bme.hu.eventr.interactor;

import java.util.ArrayList;
import java.util.List;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.model.UserModel;
import aut.bme.hu.eventr.repository.Repository;

public class EventInteractor {

    private Repository repository;


    public EventInteractor()
    {
        EventRApplication.injector.inject(this);
    }

    public List<EventModel> getEventsForUser(UserModel user)
    {
        // TODO
        return new ArrayList<>();
    }

    public EventModel saveEvent(EventModel event)
    {
        // TODO
        return event;
    }

    public EventModel updateEvent(EventModel event)
    {
        // TODO
        return event;
    }
}
