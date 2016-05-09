package aut.bme.hu.eventr;

import android.app.Application;

import aut.bme.hu.eventr.view.ViewModule;
import aut.bme.hu.eventr.DaggerEventRApplicationComponent;

public class EventRApplication extends Application {

        public static EventRApplicationComponent injector;

        @Override
        public void onCreate() {
            super.onCreate();
            injector = DaggerEventRApplicationComponent.builder().viewModule(new ViewModule(this)).build();
        }
}
