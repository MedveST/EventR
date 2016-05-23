package aut.bme.hu.eventr.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import aut.bme.hu.eventr.BuildConfig;
import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.RobolectricDaggerTestRunner;
import aut.bme.hu.eventr.TestComponent;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.interactor.UserInteractor;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.model.UserModel;
import aut.bme.hu.eventr.presenter.LoginPresenter;
import aut.bme.hu.eventr.presenter.ScheduleEventPresenter;
import aut.bme.hu.eventr.presenter.SettingsPresenter;
import aut.bme.hu.eventr.view.LoginView;
import aut.bme.hu.eventr.view.ScheduleEventView;
import aut.bme.hu.eventr.view.SettingsView;
import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertTrue;

import static aut.bme.hu.eventr.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SettingsTest {

    @Inject
    public EventInteractor eventInteractor;
    @Inject
    public UserInteractor userInteractor;

    private SettingsPresenter settingsPresenter;
    private SettingsView settingsView;

    private LoginView loginView;
    @Inject
    public LoginPresenter loginPresenter;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        ((TestComponent) EventRApplication.injector).inject(this);

        settingsView = mock(SettingsView.class);
        settingsPresenter = new SettingsPresenter();
        settingsPresenter.attachView(settingsView);

        loginView = mock(LoginView.class);
        loginPresenter.attachView(loginView);
    }

    // mostly UI
    @Test
    public void testForgetCredentials() {
        settingsPresenter.forgetCredentials();
        verify(loginView).setEmailText("");
        verify(loginView).setPassText("");
    }

    @Test
    public void testAutoDeleteExpiredEvents() {
        UserModel user = userInteractor.createWithEmailAndPass("asd@asd.asd", "asd");
        userInteractor.setActiveUser(user);

        Date date = new Date(System.currentTimeMillis() - 10000L);
        EventModel event = new EventModel("asd", date);
        event.setUserid(user.getId());
        eventInteractor.saveEvent(event);

        settingsPresenter.setAutoRemoveExpiredEvents(true);

        Call<List<EventModel>> call = eventInteractor.getEventsForUser(user);
        List<EventModel> events = new ArrayList<EventModel>();
        try {
            Response<List<EventModel>> response = call.execute();
            events = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(events.size() == 0);
    }


    @After
    public void tearDown() {
        settingsPresenter.detachView();
        eventInteractor.repository.deleteAll(EventModel.class);
        userInteractor.repository.deleteAll(UserModel.class);
    }
}
