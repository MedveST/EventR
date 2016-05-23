package aut.bme.hu.eventr.presenter;

import java.util.List;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.interactor.UserInteractor;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.view.UpcomingView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingPresenter extends Presenter<UpcomingView> {
    @Inject
    public EventInteractor eventInteractor;
    @Inject
    public UserInteractor userInteractor;

    private boolean autoRemoveExpiredEvents = false;
    public void setAutoRemoveExpiredEvents(boolean autoRemoveExpiredEvents)
    {
        this.autoRemoveExpiredEvents = autoRemoveExpiredEvents;
    }

    List<EventModel> eventsForCurrentUser;

    public UpcomingPresenter() {
        EventRApplication.injector.inject(this);
    }

    public void setupEventList()
    {
        Call<List<EventModel>> call = eventInteractor.getEventsForUser(userInteractor.activeUser);
        call.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response)
            {
                eventsForCurrentUser = response.body();
                if (autoRemoveExpiredEvents) {
                    eventInteractor.removeExpiredEvents(eventsForCurrentUser);
                }
                view.fillList(eventsForCurrentUser);
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t)
            {

            }
        });
    }


}
