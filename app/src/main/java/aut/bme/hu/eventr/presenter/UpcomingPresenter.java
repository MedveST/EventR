package aut.bme.hu.eventr.presenter;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.view.UpcomingView;

public class UpcomingPresenter extends Presenter<UpcomingView> {
    @Inject
    public EventInteractor interactor;

    public UpcomingPresenter() {
        EventRApplication.injector.inject(this);
    }
}
