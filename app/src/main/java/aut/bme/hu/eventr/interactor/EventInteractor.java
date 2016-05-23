package aut.bme.hu.eventr.interactor;

import android.app.usage.UsageEvents;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.model.UserModel;
import aut.bme.hu.eventr.network.EventsApi;
import aut.bme.hu.eventr.repository.Repository;
import retrofit2.Call;
import retrofit2.Response;

public class EventInteractor {

    @Inject
    public Repository repository;

    @Inject
    public EventsApi api;


    public EventInteractor()
    {
        EventRApplication.injector.inject(this);
    }

    public Call<List<EventModel>> getEventsForUser(UserModel user)
    {
        return api.eventsGet(new BigDecimal(user.getId()));
    }

    public void removeExpiredEvents(List<EventModel> list)
    {
        List<EventModel> listToRemove = new ArrayList<EventModel>();
        Date now = new Date();
        for (EventModel event : list) {
            if (event.getDate().before(now)) {
                removeEvent(event);
                listToRemove.add(event);
            }
        }
        list.removeAll(listToRemove);
    }

    public Call<EventModel> saveEvent(EventModel event)
    {
        return api.createEventPost(new BigDecimal(event.getUserid()), event.getTitle(), event.getDate());
    }

    public Call<EventModel> updateEvent(EventModel event)
    {
        return api.modifyEventPost(event.getTitle(), event.getDate());
    }

    public void removeEvent(EventModel event)
    {
        repository.delete(event);
    }
}
