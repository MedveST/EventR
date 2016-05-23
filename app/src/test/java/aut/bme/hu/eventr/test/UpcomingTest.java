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
import aut.bme.hu.eventr.presenter.UpcomingPresenter;
import aut.bme.hu.eventr.view.UpcomingView;
import retrofit2.Call;
import retrofit2.Response;

import static aut.bme.hu.eventr.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class UpcomingTest {

    @Inject
    public UserInteractor userInteractor;

    @Inject
    public EventInteractor eventInteractor;

    private UpcomingPresenter upcomingPresenter;
    private UpcomingView upcomingView;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        upcomingView = mock(UpcomingView.class);
        upcomingPresenter = new UpcomingPresenter();
        upcomingPresenter.attachView(upcomingView);
        ((TestComponent) EventRApplication.injector).inject(this);
    }

    @Test
    public void testSetupEventList() {
        EventModel event1 = new EventModel("xyz", new Date());
        event1.setUserid(123L);
        EventModel event2 = new EventModel("xyz", new Date());
        event2.setUserid(123L);

        eventInteractor.saveEvent(event1);
        eventInteractor.saveEvent(event2);

        UserModel user = new UserModel("xyz@sad.vd", "asd");
        user.setId(123L);
        userInteractor.setActiveUser(user);

        Call<List<EventModel>> call = eventInteractor.getEventsForUser(user);
        List<EventModel> events = new ArrayList<EventModel>();
        try {
            Response<List<EventModel>> response = call.execute();
            events = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        upcomingPresenter.setupEventList();
        verify(upcomingView).fillList(events);
    }


    @After
    public void tearDown() {
        upcomingPresenter.detachView();
        eventInteractor.repository.deleteAll(EventModel.class);
    }
}
