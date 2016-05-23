package aut.bme.hu.eventr.network.mock;

import android.net.Uri;

import java.util.List;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.model.UserModel;
import aut.bme.hu.eventr.network.Error;
import aut.bme.hu.eventr.network.GsonHelper;
import aut.bme.hu.eventr.network.NetworkConfig;
import aut.bme.hu.eventr.repository.Repository;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

public class UserMockRequestProcessor {

    @Inject
    static Repository userMockRepository;

    public UserMockRequestProcessor()
    {
        EventRApplication.injector.inject(this);
    }

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString = null;
        int responseCode;
        Headers headers = request.headers();

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "login") && request.method().equals("GET")) {
            int emailIndex = uri.getPath().indexOf("email") + 6; // len(email=)
            int emailEndIndex = uri.getPath().indexOf('&', emailIndex);
            String email = uri.getPath().substring(emailIndex, emailEndIndex);

            int passIndex = uri.getPath().indexOf("pass") + 5; // len(pass=)
            String pass = uri.getPath().substring(passIndex);

            List<UserModel> users = (List<UserModel>)userMockRepository.find(UserModel.class, "email=" + "'" + email + "'");
            UserModel user = null;
            if (users.size() == 1)
            {
                user = users.get(0);
            }
            else
            {
                user = new UserModel(email, pass);
                userMockRepository.save(user);
            }

            if ( user != null ) {
                responseString = GsonHelper.getGson().toJson(user);
                responseCode = 200;
            }
            else
            {
                Error error = new Error();
                error.setMessage("Failed to create event!");
                responseString = GsonHelper.getGson().toJson(error);
                responseCode = 400;
            }
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return MockHelper.makeResponse(request, headers, responseCode, responseString);
    }
}
