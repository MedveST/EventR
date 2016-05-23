package aut.bme.hu.eventr;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import aut.bme.hu.eventr.presenter.CalendarPresenter;
import aut.bme.hu.eventr.presenter.LoginPresenter;
import aut.bme.hu.eventr.presenter.ScheduleEventPresenter;
import aut.bme.hu.eventr.presenter.SettingsPresenter;
import aut.bme.hu.eventr.presenter.UpcomingPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {

	private Context context;

	public TestModule(Context context) {
		this.context = context;
	}

	@Provides
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	public CalendarPresenter getCalendarPresenter() {
		return new CalendarPresenter();
	}

	@Provides
	@Singleton
	public LoginPresenter getLoginPresenter() { return new LoginPresenter(); }

	@Provides
	@Singleton
	public ScheduleEventPresenter getScheduleEventPresenter() { return new ScheduleEventPresenter(); }

	@Provides
	@Singleton
	public SettingsPresenter getSettingsPresenter() {
		return new SettingsPresenter();
	}

	@Provides
	@Singleton
	public UpcomingPresenter getUpcomingPresenter() {
		return new UpcomingPresenter();
	}


}
