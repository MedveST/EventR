package aut.bme.hu.eventr;

import javax.inject.Singleton;

import aut.bme.hu.eventr.interactor.InteractorModule;
import aut.bme.hu.eventr.model.ModelModule;
import aut.bme.hu.eventr.network.mock.MockNetworkModule;
import aut.bme.hu.eventr.repository.RepositoryModule;
import aut.bme.hu.eventr.view.ViewModule;
import dagger.Component;

@Singleton
@Component(modules = {ViewModule.class, InteractorModule.class, ModelModule.class, RepositoryModule.class, MockNetworkModule.class})
public interface MockEventRApplicationComponent extends EventRApplicationComponent {

}
