package aut.bme.hu.eventr.network.mock;

import java.io.IOException;

import javax.inject.Singleton;

import aut.bme.hu.eventr.network.EventsApi;
import aut.bme.hu.eventr.network.GsonHelper;
import aut.bme.hu.eventr.network.NetworkConfig;
import aut.bme.hu.eventr.network.NetworkModule;
import aut.bme.hu.eventr.network.UnsafeClientFactory;
import aut.bme.hu.eventr.network.UsersApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@Module
public class MockNetworkModule {

    private NetworkModule networkModule = new NetworkModule();


    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        OkHttpClient.Builder clientBuilder = null;
        try {
            clientBuilder = UnsafeClientFactory.getUnsafeClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (clientBuilder == null) {
            throw new RuntimeException("HttpClient cannot be initialized!");
        }

        return clientBuilder;
    }

    @Provides
    @Singleton
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


    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson())).build();
    }

    @Provides
    @Singleton
    public EventsApi provideEventsApi(Retrofit retrofit) {
        return retrofit.create(EventsApi.class);
    }

    @Provides
    @Singleton
    public UsersApi provideUsersApi(Retrofit retrofit) {
        return retrofit.create(UsersApi.class);
    }
}
