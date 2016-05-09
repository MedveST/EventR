package aut.bme.hu.eventr.interactor;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.model.UserModel;
import aut.bme.hu.eventr.repository.Repository;

public class UserInteractor {

    private Repository repository;
    private UserModel activeUser;


    public UserInteractor()
    {
        EventRApplication.injector.inject(this);
    }

    public void setActiveUser(UserModel user)
    {
        activeUser = user;
    }

    public UserModel findByEmailAndPass(String email, String pass)
    {
        // TODO
        return null;
    }

    public UserModel createWithEmailAndPass(String email, String pass)
    {
        // TODO
        return null;
    }
}