package aut.bme.hu.eventr.presenter;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.view.SettingsView;

public class SettingsPresenter extends Presenter<SettingsView> {
    public SettingsPresenter() {
        EventRApplication.injector.inject(this);
    }

    @Inject
    UpcomingPresenter upcomingPresenter;
    @Inject
    LoginPresenter loginPresenter;

    public void setAutoRemoveExpiredEvents(boolean autoRemoveExpiredEvents)
    {
        upcomingPresenter.setAutoRemoveExpiredEvents(autoRemoveExpiredEvents);
    }

    public void forgetCredentials()
    {
        loginPresenter.clearData();
    }
}
