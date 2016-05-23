package aut.bme.hu.eventr;


import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

public class TestHelper {

	public static void setTestInjector() {
		ShadowLog.stream = System.out;
		EventRApplication application = (EventRApplication) RuntimeEnvironment.application;
		MockEventRApplicationComponent injector = DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).build();
		application.injector = injector;
	}
}
