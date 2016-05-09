package aut.bme.hu.eventr.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "")
public class UserModel
{
    @SerializedName("id")
    private BigDecimal id = null;

    @SerializedName("email")
    private String email = null;

    @SerializedName("pass")
    private String pass = null;


    public UserModel(){
    }

    public UserModel(String email, String pass){
        this.email = email;
        this.pass = pass;
    }


    /**
     * Unique identifier representing a specific user
     **/
    @ApiModelProperty(value = "Unique identifier representing a specific user")
    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }


    /**
     * E-mail address of the user
     **/
    @ApiModelProperty(value = "E-mail address of the user")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Password of the user
     **/
    @ApiModelProperty(value = "Password of the user")
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserModel user = (UserModel) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(pass, user.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, pass);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class User {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    pass: ").append(toIndentedString(pass)).append("\n");
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
