package aut.bme.hu.eventr.network.mock;

import android.net.Uri;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.network.GsonHelper;
import aut.bme.hu.eventr.network.NetworkConfig;
import aut.bme.hu.eventr.repository.Repository;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

public class EventMockRequestProcessor {

    @Inject
    static Repository eventMockRepository;
   //static List<Person> peopleList = new ArrayList<>();
   //static boolean isInitialised = false;

   //public static Person testP1 = new Person("Network Test 1");
   //public static Person testP2 = new Person("Network Test 2");

    public List<EventModel> events(BigDecimal userId)
    {
        return null;
    }

    public EventModel createEvent(BigDecimal userId)
    {
        return null;
    }

    public EventModel modifyEvent(BigDecimal userId)
    {
        return null;
    }

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString = null;
        int responseCode;
        Headers headers = request.headers();

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "events") && request.method().equals("GET")) {
            int emailIndex = uri.getPath().indexOf("email") + 6; // len(email=)
            int emailEndIndex = uri.getPath().indexOf('&', emailIndex);
            String email = uri.getPath().substring(emailIndex, emailEndIndex);

            int passIndex = uri.getPath().indexOf("pass") + 5; // len(pass=)
            int passEndIndex = uri.getPath().indexOf('&', passIndex);
            String pass = uri.getPath().substring(passIndex, passEndIndex);


            //responseString = GsonHelper.getGson().toJson(peopleList);
            responseCode = 200;
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "createEvent") && request.method().equals("POST")) {
            int startOfData = uri.getPath().lastIndexOf('/');
            String name = uri.getPath().substring(startOfData + 1);
            //peopleList.add(new Person(name));

            responseString = "";
            responseCode = 200;
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "modifyEvent") && request.method().equals("POST")) {
            int startOfData = uri.getPath().lastIndexOf('/');
            String name = uri.getPath().substring(startOfData + 1);
            //peopleList.add(new Person(name));

            responseString = "";
            responseCode = 200;
        } else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return MockHelper.makeResponse(request, headers, responseCode, responseString);
    }
}
