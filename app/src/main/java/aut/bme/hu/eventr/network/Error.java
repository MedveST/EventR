package aut.bme.hu.eventr.network;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "")
public class Error   {

    @SerializedName("code")
    private Integer code = null;

    @SerializedName("message")
    private String message = null;

    @SerializedName("fields")
    private String fields = null;



    /**
     **/
    @ApiModelProperty(value = "")
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     **/
    @ApiModelProperty(value = "")
    public String getFields() {
        return fields;
    }
    public void setFields(String fields) {
        this.fields = fields;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Error error = (Error) o;
        return Objects.equals(code, error.code) &&
                Objects.equals(message, error.message) &&
                Objects.equals(fields, error.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, fields);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Error {\n");

        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
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
