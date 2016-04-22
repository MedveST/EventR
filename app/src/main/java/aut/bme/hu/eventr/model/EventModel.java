package aut.bme.hu.eventr.model;

import com.orm.dsl.Table;

import java.io.Serializable;

@Table
// Serializable required for passing in a parcel
public class EventModel implements Serializable
{
    private Long id;
    private String title;
    private Long date;

    public EventModel(){
    }

    public EventModel(String title, Long date){
        this.title = title;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() { return title; }

    public Long getDate()
    {
        return date;
    }
}
