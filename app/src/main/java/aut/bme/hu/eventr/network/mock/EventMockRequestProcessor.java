package aut.bme.hu.eventr.network.mock;

import android.net.Uri;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import aut.bme.hu.eventr.EventRApplication;
import aut.bme.hu.eventr.model.EventModel;
import aut.bme.hu.eventr.network.Error;
import aut.bme.hu.eventr.network.GsonHelper;
import aut.bme.hu.eventr.network.NetworkConfig;
import aut.bme.hu.eventr.repository.Repository;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

public class EventMockRequestProcessor {

    @Inject
    static Repository eventMockRepository;

    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString = null;
        int responseCode;
        Headers headers = request.headers();

        if (uri.getPath().equals(NetworkConfig.ENDPOINT_PREFIX + "events") && request.method().equals("GET")) {
            int useridIndex = uri.toString().indexOf("userid") + 7; // len(id=)
            Long userid = Long.parseLong(uri.toString().substring(useridIndex));

            List<EventModel> events = (List<EventModel>)eventMockRepository.find(EventModel.class, "userid=" + userid.toString());

            if (events != null) {
                responseString = GsonHelper.getGson().toJson(events);
                responseCode = 200;
            }
            else
            {
                Error error = new Error();
                error.setMessage("Failed to list events!");
                responseString = GsonHelper.getGson().toJson(error);
                responseCode = 400;
            }
        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "createEvent") && request.method().equals("POST")) {
            int useridIndex = uri.toString().indexOf("userid") + 7; // len(id=)
            int useridEndIndex = uri.toString().indexOf('&', useridIndex);
            Long userid = Long.parseLong(uri.toString().substring(useridIndex, useridEndIndex));

            int nameIndex = uri.toString().indexOf("name") + 5; // len(name=)
            int nameEndIndex = uri.toString().indexOf('&', nameIndex);
            String name = uri.toString().substring(nameIndex, nameEndIndex);

            int dateIndex = uri.toString().indexOf("date") + 5; // len(date=)
            Date date = GsonHelper.getGson().fromJson(uri.toString().substring(dateIndex), Date.class);

            EventModel event = new EventModel(name, date);
            event.setUserid(userid);
            Long id = eventMockRepository.save(event);
            if ( id != 0L ) {
                event.setId(id);
                responseString = GsonHelper.getGson().toJson(event);
                responseCode = 200;
            }
            else
            {
                Error error = new Error();
                error.setMessage("Failed to create event!");
                responseString = GsonHelper.getGson().toJson(error);
                responseCode = 400;
            }

        } else if (uri.getPath().startsWith(NetworkConfig.ENDPOINT_PREFIX + "modifyEvent") && request.method().equals("POST")) {;
            int idIndex = uri.toString().indexOf("id") + 3; // len(id=)
            int idEndIndex = uri.toString().indexOf('&', idIndex);
            Long id = Long.parseLong(uri.toString().substring(idIndex, idEndIndex));

            int nameIndex = uri.toString().indexOf("name") + 5; // len(name=)
            int nameEndIndex = uri.toString().indexOf('&', nameIndex);
            String name = uri.toString().substring(nameIndex, nameEndIndex);

            int dateIndex = uri.toString().indexOf("date") + 5; // len(date=)
            Date date = GsonHelper.getGson().fromJson(uri.toString().substring(dateIndex), Date.class);

            EventModel event = new EventModel(name, date);
            event.setId(id);

            EventModel updatedEvent = (EventModel)eventMockRepository.update(EventModel.class, id, event);
            if (updatedEvent != null) {
                event.setId(id);
                responseString = GsonHelper.getGson().toJson(event);
                responseCode = 200;
            } else {
                Error error = new Error();
                error.setMessage("Failed to modify event!");
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
