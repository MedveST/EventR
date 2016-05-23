package aut.bme.hu.eventr.presenter;

import android.util.Log;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.interactor.UserInteractor;
import aut.bme.hu.eventr.model.UserModel;
import aut.bme.hu.eventr.view.LoginView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends Presenter<LoginView> {

    @Inject
    public UserInteractor interactor;

    public LoginPresenter() {
        EventRApplication.injector.inject(this);
    }

    @Override
    public void attachView(LoginView screen)
    {
        super.attachView(screen);
    }

    public void loginOrSignUp(String email, String pass )
    {
        if ( !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() )
        {
            return;
        }

        if ( pass.length() == 0 )
        {
            return;
        }

        Call<UserModel> call = interactor.findOrInsert(email, pass);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response)
            {
                interactor.setActiveUser(response.body());
                view.leaveScreen();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t)
            {
                Log.e("LoginPresenter", "User didn't exist and could not be created");
            }
        });

    }

    public void clearData()
    {
        view.setEmailText("");
        view.setPassText("");
    }
}
