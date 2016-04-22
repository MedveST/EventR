package aut.bme.hu.eventr.presenter;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.view.ScheduleEventView;

public class ScheduleEventPresenter extends Presenter<ScheduleEventView> {
    @Inject
    public EventInteractor interactor;

    private EventModel eventUnderConstruction;
    private boolean isEdititingExisting;

    public ScheduleEventPresenter() {
        EventRApplication.injector.inject(this);
    }

    public void setupCreate(Long startingDateAndTime, String startingTitle)
    {
        // TODO
    }

    public void setupEdit(EventModel eventToEdit)
    {
        // TODO
    }
}
