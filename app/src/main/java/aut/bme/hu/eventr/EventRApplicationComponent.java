package aut.bme.hu.eventr;

import javax.inject.Singleton;

import aut.bme.hu.eventr.interactor.UserInteractor;
import aut.bme.hu.eventr.network.NetworkModule;
import aut.bme.hu.eventr.network.mock.EventMockRequestProcessor;
import aut.bme.hu.eventr.network.mock.MockNetworkModule;
import aut.bme.hu.eventr.network.mock.UserMockRequestProcessor;
import aut.bme.hu.eventr.presenter.CalendarPresenter;
import aut.bme.hu.eventr.presenter.ScheduleEventPresenter;
import aut.bme.hu.eventr.presenter.SettingsPresenter;
import aut.bme.hu.eventr.presenter.UpcomingPresenter;
import aut.bme.hu.eventr.repository.Repository;
import aut.bme.hu.eventr.repository.RepositoryModule;
import aut.bme.hu.eventr.repository.SugarORMRepository;
import aut.bme.hu.eventr.view.CalendarActivity;
import aut.bme.hu.eventr.view.LoginActivity;
import aut.bme.hu.eventr.view.LoginView;
import aut.bme.hu.eventr.view.ScheduleEventActivity;
import aut.bme.hu.eventr.view.SettingsActivity;
import aut.bme.hu.eventr.view.UpcomingActivity;
import dagger.Component;
import aut.bme.hu.eventr.interactor.InteractorModule;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.model.ModelModule;
import aut.bme.hu.eventr.presenter.LoginPresenter;
import aut.bme.hu.eventr.view.ViewModule;

@Singleton
@Component(modules = {ViewModule.class, InteractorModule.class, ModelModule.class, RepositoryModule.class, MockNetworkModule.class})
public interface EventRApplicationComponent {
        void inject(LoginActivity loginActivity);

        void inject(CalendarActivity calendarActivity);

        void inject(ScheduleEventActivity scheduleEventActivity);

        void inject(SettingsActivity settingsActivity);

        void inject(UpcomingActivity upcomingActivity);

        void inject(LoginPresenter loginPresenter);

        void inject(CalendarPresenter calendarPresenter);

        void inject(ScheduleEventPresenter scheduleEventPresenter);

        void inject(SettingsPresenter settingsPresenter);

        void inject(UpcomingPresenter upcomingPresenter);

        void inject(EventInteractor eventInteractor);

        void inject(UserInteractor userInteractor);

        void inject(Repository repository);

        void inject(EventMockRequestProcessor eventMockRequestProcessor);

        void inject(UserMockRequestProcessor userMockRequestProcessor);
}
