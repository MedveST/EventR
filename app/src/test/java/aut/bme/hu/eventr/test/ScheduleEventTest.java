package aut.bme.hu.eventr.test;

import android.app.usage.UsageEvents;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;

import aut.bme.hu.eventr.BuildConfig;
import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.RobolectricDaggerTestRunner;
import aut.bme.hu.eventr.TestComponent;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.interactor.UserInteractor;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.model.UserModel;
import aut.bme.hu.eventr.presenter.ScheduleEventPresenter;
import aut.bme.hu.eventr.view.ScheduleEventView;
import retrofit2.Call;

import static junit.framework.Assert.assertTrue;

import static aut.bme.hu.eventr.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ScheduleEventTest {

    @Inject
    public EventInteractor eventInteractor;

    @Inject
    public UserInteractor userInteractor;

    private ScheduleEventPresenter scheduleEventPresenter;
    private ScheduleEventView scheduleEventView;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        scheduleEventView = mock(ScheduleEventView.class);
        scheduleEventPresenter = new ScheduleEventPresenter();
        scheduleEventPresenter.attachView(scheduleEventView);
        ((TestComponent)EventRApplication.injector).inject(this);
    }

    @Test
    public void testSetupCreate() {
        scheduleEventPresenter.setupCreate();
        assertTrue(scheduleEventPresenter.getEventUnderConstruction() != null);
    }

    @Test
    public void testSetupEdit() {
        String title = "xyz@sda.xs";
        Date date = new Date();
        EventModel event = new EventModel(title, date);
        scheduleEventPresenter.setupEdit(event);

        assertTrue(scheduleEventPresenter.getEventUnderConstruction().getTitle().equals(title));
        assertTrue(scheduleEventPresenter.getEventUnderConstruction().getDate().equals(date));
    }

    @Test
    public void testOnDateChanged() {
        int newHour = 11;
        int newMinute = 24;

        scheduleEventPresenter.setupCreate();
        scheduleEventPresenter.onDateChanged(newHour, newMinute);

        long currentDate = scheduleEventPresenter.getEventUnderConstruction().getDate().getTime();
        currentDate -= currentDate % (3600L * 24L * 1000L);
        currentDate += newHour * 3600L * 1000L;
        currentDate += newMinute * 60L * 1000L;

        assertTrue(scheduleEventPresenter.getEventUnderConstruction().getDate().getTime() == currentDate);
    }

    @Test
    public void testOnTitleChanged() {
        String newTitle = "xyz";

        scheduleEventPresenter.setupCreate();
        scheduleEventPresenter.onTitleChanged(newTitle);
        assertTrue(scheduleEventPresenter.getEventUnderConstruction().getTitle().equals(newTitle));
    }

    @Test
    public void testOnRemoveNew() {
        scheduleEventPresenter.setupCreate();

        assertTrue(scheduleEventPresenter.getEventUnderConstruction() != null);

        scheduleEventPresenter.onRemove();

        assertTrue(scheduleEventPresenter.getEventUnderConstruction() == null);
        verify(scheduleEventView).leaveScreen();
    }

    @Test
    public void testOnRemoveExisting() {

        EventModel event = new EventModel("adsdasd", new Date());
        event.setUserid(123L);
        eventInteractor.saveEvent(event);

        assertTrue(event.getId() != null);
        assertTrue(eventInteractor.repository.findById(EventModel.class, event.getId()) != null);

        scheduleEventPresenter.setupEdit(event);

        assertTrue(scheduleEventPresenter.getEventUnderConstruction() != null);
        scheduleEventPresenter.onRemove();
        assertTrue(eventInteractor.repository.findById(EventModel.class, event.getId()) == null);
        assertTrue(scheduleEventPresenter.getEventUnderConstruction() == null);

        verify(scheduleEventView).leaveScreen();
    }

    @Test
    public void testOnDoneNew() {
        UserModel user = userInteractor.createWithEmailAndPass("asd@asd.asd", "asd");
        userInteractor.setActiveUser(user);

        scheduleEventPresenter.setupCreate();
        assertTrue(eventInteractor.repository.findById(EventModel.class, scheduleEventPresenter.getEventUnderConstruction().getId()) == null);

        EventModel eventUnderConstruction = scheduleEventPresenter.getEventUnderConstruction();
        assertTrue(eventUnderConstruction != null);
        scheduleEventPresenter.onTitleChanged("xyz");
        assertTrue(eventUnderConstruction.getTitle().equals("xyz"));

        scheduleEventPresenter.onDone();

        Call<EventModel> call = eventInteractor.saveEvent(eventUnderConstruction);
        EventModel savedEvent = null;
        try {
            savedEvent = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(savedEvent != null);
        assertTrue(savedEvent.getTitle().equals("xyz"));
        assertTrue(savedEvent.getUserid().equals(user.getId()));
        assertTrue(scheduleEventPresenter.getEventUnderConstruction() == null);

        verify(scheduleEventView).leaveScreen();
    }

    @Test
    public void testOnDoneExisting() {
        UserModel user = userInteractor.createWithEmailAndPass("asd@asd.asd", "asd");
        userInteractor.setActiveUser(user);

        EventModel event = new EventModel("adsdasd", new Date());
        event.setUserid(user.getId());
        eventInteractor.saveEvent(event);
        assertTrue(event.getId() != null);

        assertTrue(eventInteractor.repository.findById(EventModel.class, event.getId()) != null);

        scheduleEventPresenter.setupEdit(event);

        assertTrue(scheduleEventPresenter.getEventUnderConstruction() != null);
        scheduleEventPresenter.onTitleChanged("xyz");
        assertTrue(scheduleEventPresenter.getEventUnderConstruction().getTitle().equals("xyz"));

        scheduleEventPresenter.onDone();

        Call<EventModel> call = eventInteractor.updateEvent(scheduleEventPresenter.getEventUnderConstruction());
        EventModel savedEvent = null;
        try {
            savedEvent = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(savedEvent != null);
        assertTrue(savedEvent.getTitle().equals("xyz"));
        assertTrue(savedEvent.getUserid().equals(user.getId()));
        assertTrue(scheduleEventPresenter.getEventUnderConstruction() == null);

        verify(scheduleEventView).leaveScreen();
    }

    @After
    public void tearDown() {
        scheduleEventPresenter.detachView();
        eventInteractor.repository.deleteAll(EventModel.class);
        userInteractor.repository.deleteAll(UserModel.class);
    }
}
