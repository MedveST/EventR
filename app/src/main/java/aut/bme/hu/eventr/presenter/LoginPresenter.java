package aut.bme.hu.eventr.presenter;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.interactor.UserInteractor;
import aut.bme.hu.eventr.view.LoginView;

public class LoginPresenter extends Presenter<LoginView> {

    @Inject
    public UserInteractor interactor;

    public LoginPresenter() {
        EventRApplication.injector.inject(this);
    }

}
