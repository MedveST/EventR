package aut.bme.hu.eventr.presenter;

import java.util.Date;

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

    public void selectDate(Date date)
    {
        final Long currentDayMillis = System.currentTimeMillis() % (3600L * 1000L * 24L) + 1;
        if (date.after(new Date(System.currentTimeMillis() - currentDayMillis)))
        {
            view.createEventOnDay();
        }
    }
}
