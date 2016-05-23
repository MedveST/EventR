package aut.bme.hu.eventr.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import aut.bme.hu.eventr.BuildConfig;
import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.RobolectricDaggerTestRunner;
import aut.bme.hu.eventr.TestComponent;
import aut.bme.hu.eventr.interactor.UserInteractor;
import aut.bme.hu.eventr.model.UserModel;
import aut.bme.hu.eventr.presenter.LoginPresenter;
import aut.bme.hu.eventr.view.LoginView;

import static aut.bme.hu.eventr.TestHelper.setTestInjector;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginTest {

    @Inject
    public UserInteractor userInteractor;

    private LoginPresenter loginPresenter;
    private LoginView loginView;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        loginView = mock(LoginView.class);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(loginView);
        ((TestComponent) EventRApplication.injector).inject(this);
    }

    @Test
    public void testLoginAndSignUp() {
        String email = "medve@gmail.com";
        String pass = "muszterdzsusztisz";

        loginPresenter.loginOrSignUp(email, pass);
        verify(loginView).leaveScreen();
        assertTrue(userInteractor.activeUser != null);
        assertTrue(userInteractor.activeUser.getEmail().equals(email));
        assertTrue(userInteractor.activeUser.getPass().equals(pass));

        assertTrue(userInteractor.findByEmail(email).getId().equals(userInteractor.activeUser.getId()));

        List<UserModel> list = (List<UserModel>)userInteractor.repository.find(UserModel.class, "email=" + "'" + email + "'");
        assertTrue((list.size()) == 1);


        loginPresenter.loginOrSignUp(email, pass);
        assertTrue(userInteractor.activeUser != null);
        assertTrue(userInteractor.activeUser.getEmail().equals(email));
        assertTrue(userInteractor.activeUser.getPass().equals(pass));

        assertTrue(userInteractor.findByEmail(email).getId().equals(userInteractor.activeUser.getId()));

        list = (List<UserModel>)userInteractor.repository.find(UserModel.class, "email=" + "'" + email + "'");
        assertTrue((list.size()) == 1);
    }

    @Test
    public void testInvalidEmail() {
        String email = "medvesgmail.";
        String pass = "muszterdzsusztisz";

        loginPresenter.loginOrSignUp(email, pass);
        verify(loginView, times(0)).leaveScreen();
        assertTrue(userInteractor.activeUser == null);
    }

    @After
    public void tearDown() {
        loginPresenter.detachView();
        userInteractor.repository.deleteAll(UserModel.class);
    }
}
