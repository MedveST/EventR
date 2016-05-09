package aut.bme.hu.eventr.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// Serializable required for passing in a parcel
@ApiModel(description = "")
public class EventModel implements Serializable
{
    @SerializedName("id")
    private BigDecimal id = null;

    @SerializedName("title")
    private String title = null;

    @SerializedName("date")
    private Date date = null;


    public EventModel(){
    }

    public EventModel(String title, Date date){
        this.title = title;
        this.date = date;
    }



    /**
     * Unique identifier representing a specific product event
     **/
    @ApiModelProperty(value = "Unique identifier representing a specific product event")
    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }


    /**
     * Title/Short description of the event
     **/
    @ApiModelProperty(value = "Title/Short description of the event")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Date and time of the event start
     **/
    @ApiModelProperty(value = "Date and time of the event start")
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventModel event = (EventModel) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(title, event.title) &&
                Objects.equals(date, event.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Event {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    date: ").append(toIndentedString(date)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
