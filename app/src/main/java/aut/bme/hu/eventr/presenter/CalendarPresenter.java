package aut.bme.hu.eventr.presenter;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.view.CalendarView;

public class CalendarPresenter extends Presenter<CalendarView> {
    @Inject
    public EventInteractor interactor;

    public CalendarPresenter() {
        EventRApplication.injector.inject(this);
    }
}
