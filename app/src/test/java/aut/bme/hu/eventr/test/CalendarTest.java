package aut.bme.hu.eventr.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.Date;

import aut.bme.hu.eventr.BuildConfig;
import aut.bme.hu.eventr.RobolectricDaggerTestRunner;
import aut.bme.hu.eventr.presenter.CalendarPresenter;
import aut.bme.hu.eventr.view.CalendarView;

import static aut.bme.hu.eventr.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CalendarTest {

    private CalendarPresenter calendarPresenter;
    private CalendarView calendarView;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        calendarView = mock(CalendarView.class);
        calendarPresenter = new CalendarPresenter();
        calendarPresenter.attachView(calendarView);
    }

    @Test
    public void testSelectDate() {
        Date date = new Date(System.currentTimeMillis() + 3600L * 1000L * 24L);
        calendarPresenter.selectDate(date);
        verify(calendarView).createEventOnDay();
    }


    @After
    public void tearDown() {
        calendarPresenter.detachView();
    }
}
