package aut.bme.hu.eventr.network.mock;

import java.io.IOException;

import javax.inject.Singleton;

import aut.bme.hu.eventr.network.EventsApi;
import aut.bme.hu.eventr.network.NetworkModule;
import aut.bme.hu.eventr.network.UsersApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

@Module
public class MockNetworkModule extends NetworkModule{

    private NetworkModule networkModule = new NetworkModule();


    @Provides
    @Singleton
    @Override
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        builder.interceptors().add(0, new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return MockHttpServer.call(request);
            }
        });
        return builder.build();
    }
}
