package aut.bme.hu.eventr.presenter;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.view.ScheduleEventView;

public class ScheduleEventPresenter extends Presenter<ScheduleEventView> {
    @Inject
    public EventInteractor interactor;

    public ScheduleEventPresenter() {
        EventRApplication.injector.inject(this);
    }
}
