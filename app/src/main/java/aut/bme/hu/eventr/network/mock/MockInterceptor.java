package aut.bme.hu.eventr.network.mock;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import aut.bme.hu.eventr.network.NetworkConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MockInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return process(chain.request());
    }

    public Response process(Request request) {

        Uri uri = Uri.parse(request.url().toString());

        Log.d("Test Http Client", "URL call: " + uri.toString());
        Headers headers = request.headers();

        if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "events")) {
            return EventMockRequestProcessor.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "login")) {
            return UserMockRequestProcessor.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "createEvent")) {
            return UserMockRequestProcessor.process(request);
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "editEvent")) {
            return EventMockRequestProcessor.process(request);
        } else {
            return MockHelper.makeResponse(request, headers, 404, "Unknown");
        }
    }
}