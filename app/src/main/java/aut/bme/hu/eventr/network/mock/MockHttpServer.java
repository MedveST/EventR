package aut.bme.hu.eventr.network.mock;

/**
 * Created by mobsoft on 2016. 05. 09..
 */
import okhttp3.Request;
import okhttp3.Response;

public class MockHttpServer {

    public static Response call(Request request) {
        MockInterceptor mockInterceptor = new MockInterceptor();
        return mockInterceptor.process(request);
    }
}