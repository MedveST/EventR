package aut.bme.hu.eventr;

import javax.inject.Singleton;

import aut.bme.hu.eventr.interactor.InteractorModule;
import aut.bme.hu.eventr.model.ModelModule;
import aut.bme.hu.eventr.network.mock.EventMockRequestProcessor;
import aut.bme.hu.eventr.network.mock.MockNetworkModule;
import aut.bme.hu.eventr.network.mock.UserMockRequestProcessor;
import aut.bme.hu.eventr.repository.RepositoryModule;
import aut.bme.hu.eventr.test.LoginTest;
import aut.bme.hu.eventr.test.ScheduleEventTest;
import aut.bme.hu.eventr.test.SettingsTest;
import aut.bme.hu.eventr.test.UpcomingTest;
import dagger.Component;

@Singleton
@Component(modules = {TestModule.class, InteractorModule.class, ModelModule.class, RepositoryModule.class, MockNetworkModule.class})
public interface TestComponent extends MockEventRApplicationComponent {

    void inject(LoginTest loginTest);

    void inject(ScheduleEventTest scheduleEventTest);

    void inject(SettingsTest settingsTest);

    void inject(UpcomingTest scheduleEventTest);


}
