package aut.bme.hu.eventr.presenter;

import java.util.Date;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.interactor.UserInteractor;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.view.ScheduleEventView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleEventPresenter extends Presenter<ScheduleEventView> {
    @Inject
    public EventInteractor eventInteractor;
    @Inject
    public UserInteractor userInteractor;

    private EventModel eventUnderConstruction;
    private boolean isEdititingExisting = false;

    public ScheduleEventPresenter() {
        EventRApplication.injector.inject(this);
    }

    public EventModel getEventUnderConstruction()
    {
        return eventUnderConstruction;
    }

    public void setupCreate()
    {
        eventUnderConstruction = new EventModel("Event title", new Date());
        isEdititingExisting = false;
    }

    public void setupEdit(EventModel eventToEdit)
    {
        eventUnderConstruction = eventToEdit;
        isEdititingExisting = true;
    }

    public void onTitleChanged(String newTitle)
    {
        eventUnderConstruction.setTitle(newTitle);
    }

    public void onDateChanged(int newHour, int newMinute)
    {
        long currentDate = eventUnderConstruction.getDate().getTime();
        currentDate -= currentDate % (3600L * 24L * 1000L);
        currentDate += newHour * 3600L * 1000L;
        currentDate += newMinute * 60L * 1000L;
        eventUnderConstruction.setDate(new Date(currentDate));
    }

    public void onDone()
    {
        eventUnderConstruction.setUserid(userInteractor.activeUser.getId());
        if (isEdititingExisting)
        {
            Call<EventModel> call = eventInteractor.updateEvent(eventUnderConstruction);
            call.enqueue(new Callback<EventModel>() {
                @Override
                public void onResponse(Call<EventModel> call, Response<EventModel> response)
                {
                    eventUnderConstruction = null;
                    view.leaveScreen();
                }

                @Override
                public void onFailure(Call<EventModel> call, Throwable t)
                {

                }
            });
        }
        else {

            Call<EventModel> call = eventInteractor.saveEvent(eventUnderConstruction);
            call.enqueue(new Callback<EventModel>() {
                @Override
                public void onResponse(Call<EventModel> call, Response<EventModel> response)
                {
                    eventUnderConstruction = null;
                    view.leaveScreen();
                }

                @Override
                public void onFailure(Call<EventModel> call, Throwable t)
                {

                }
            });
        }
    }

    public void onRemove()
    {
        if (isEdititingExisting) {
            eventInteractor.removeEvent(eventUnderConstruction);
        }
        eventUnderConstruction = null;
        view.leaveScreen();
    }
}
