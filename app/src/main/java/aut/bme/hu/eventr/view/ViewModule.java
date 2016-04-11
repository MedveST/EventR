package aut.bme.hu.eventr.view;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import aut.bme.hu.eventr.presenter.LoginPresenter;

@Module
public class ViewModule {
    private Context context;

    public ViewModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public LoginPresenter getMainPresenter() {
        return new LoginPresenter();
    }
}
