package aut.bme.hu.eventr.interactor;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.model.UserModel;
import aut.bme.hu.eventr.network.EventsApi;
import aut.bme.hu.eventr.network.UsersApi;
import aut.bme.hu.eventr.repository.Repository;
import retrofit2.Call;

public class UserInteractor {

    @Inject
    public Repository repository;
    public UserModel activeUser;

    @Inject
    public UsersApi api;

    public UserInteractor()
    {
        EventRApplication.injector.inject(this);
    }

    public void setActiveUser(UserModel user)
    {
        activeUser = user;
    }

    public Call<UserModel> findOrInsert(String email, String pass)
    {
        return api.loginGet(email, pass);
    }

    public UserModel findByEmail(String email)
    {
        List<UserModel> users = (List<UserModel>)repository.find(UserModel.class, "email=" + "'" + email + "'");
        if (users.size() == 1)
        {
            return users.get(0);
        }
        return null;
    }

    public UserModel createWithEmailAndPass(String email, String pass)
    {
        UserModel user = new UserModel(email, pass);
        Long id = repository.save(user);
        if (id != 0)
        {
            user.setId(id);
            return user;
        }
        return null;
    }
}