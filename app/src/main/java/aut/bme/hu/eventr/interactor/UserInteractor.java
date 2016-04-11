package aut.bme.hu.eventr.interactor;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.model.UserModel;

/**
 * Created by Medve on 2016. 04. 11..
 */
public class UserInteractor {
    @Inject
    UserModel model;

    public UserInteractor()
    {
        EventRApplication.injector.inject(this);
    }

    public String getName()
    {
        return model.getName();
    }
}