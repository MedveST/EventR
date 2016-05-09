package aut.bme.hu.eventr;

import javax.inject.Singleton;

import aut.bme.hu.eventr.interactor.UserInteractor;
import aut.bme.hu.eventr.presenter.CalendarPresenter;
import aut.bme.hu.eventr.presenter.ScheduleEventPresenter;
import aut.bme.hu.eventr.presenter.SettingsPresenter;
import aut.bme.hu.eventr.presenter.UpcomingPresenter;
import aut.bme.hu.eventr.repository.Repository;
import aut.bme.hu.eventr.repository.RepositoryModule;
import aut.bme.hu.eventr.repository.SugarORMRepository;
import aut.bme.hu.eventr.view.LoginActivity;
import dagger.Component;
import aut.bme.hu.eventr.interactor.InteractorModule;
import aut.bme.hu.eventr.interactor.EventInteractor;
import aut.bme.hu.eventr.model.ModelModule;
import aut.bme.hu.eventr.presenter.LoginPresenter;
import aut.bme.hu.eventr.view.ViewModule;

@Singleton
@Component(modules = {ViewModule.class, InteractorModule.class, ModelModule.class, RepositoryModule.class})
public interface EventRApplicationComponent {
        void inject(LoginActivity mainActivity);

        void inject(LoginPresenter loginPresenter);

        void inject(CalendarPresenter calendarPresenter);

        void inject(ScheduleEventPresenter scheduleEventPresenter);

        void inject(SettingsPresenter settingsPresenter);

        void inject(UpcomingPresenter upcomingPresenter);

        void inject(EventInteractor eventInteractor);

        void inject(UserInteractor userInteractor);

        void inject(Repository repository);
}
