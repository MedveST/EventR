package aut.bme.hu.eventr.presenter;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.view.SettingsView;

public class SettingsPresenter extends Presenter<SettingsView> {
    public SettingsPresenter() {
        EventRApplication.injector.inject(this);
    }
}
