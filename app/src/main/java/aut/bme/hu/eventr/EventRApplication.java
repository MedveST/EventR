package aut.bme.hu.eventr;

import android.app.Application;
import android.os.Build;

import com.orm.SugarApp;
import com.orm.SugarContext;

import aut.bme.hu.eventr.presenter.LoginPresenter;
import aut.bme.hu.eventr.view.ViewModule;
import aut.bme.hu.eventr.DaggerEventRApplicationComponent;
import aut.bme.hu.eventr.DaggerMockEventRApplicationComponent;

public class EventRApplication extends SugarApp {

        public static EventRApplicationComponent injector;

        @Override
        public void onCreate() {
            super.onCreate();

            if (BuildConfig.IS_MOCK)
            {
                injector = DaggerMockEventRApplicationComponent.builder().viewModule(new ViewModule(this)).build();
            }
            else
            {
                injector = DaggerEventRApplicationComponent.builder().viewModule(new ViewModule(this)).build();
            }
        }
}
