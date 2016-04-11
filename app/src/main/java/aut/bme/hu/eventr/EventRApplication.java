package aut.bme.hu.eventr;

import android.app.Application;

import aut.bme.hu.eventr.view.ViewModule;
import hu.bme.aut.amorg.examples.eventr.DaggerEventRApplicationComponent;

public class EventRApplication extends Application {

        public static EventRApplicationComponent injector;

        @Override
        public void onCreate() {
            super.onCreate();
            injector = DaggerEventRApplicationComponent.builder().viewModule(new ViewModule(this)).build();
        }
}
