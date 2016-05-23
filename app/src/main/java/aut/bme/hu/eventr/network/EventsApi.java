package aut.bme.hu.eventr.network;

import retrofit2.Call;
import retrofit2.http.*;

import aut.bme.hu.eventr.model.EventModel;
import java.util.Date;
import java.math.BigDecimal;

import java.util.List;

public interface EventsApi {

    /**
     * Create Event
     * The Save Event endpoint stores the information about an event.
     * @param title Title/Short description of the event we are creating
     * @param date Date and time of the start of the event we are creating
     * @return Call<Event>
     */

    @POST("createEvent")
    Call<EventModel> createEventPost(
            @Query("userid") BigDecimal userid, @Query("title") String title, @Query("date") Date date
    );


    /**
     * Event List
     * The Events endpoint returns a list of upcoming events for a specific user.
     * @param userid UserId belonging to the user we want to get the upcoming events of.
     * @return Call<List<Event>>
     */

    @GET("events")
    Call<List<EventModel>> eventsGet(
            @Query("userid") BigDecimal userid
    );


    /**
     * Create Event
     * The Modify Event endpoint modifies information about an event.
     * @param title Title/Short description of the event we are creating
     * @param date Date and time of the start of the event we are creating
     * @return Call<Event>
     */

    @POST("modifyEvent")
    Call<EventModel> modifyEventPost(
            @Query("title") String title, @Query("date") Date date
    );


}
