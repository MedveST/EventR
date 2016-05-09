package aut.bme.hu.eventr.network;

import aut.bme.hu.eventr.model.UserModel;

import retrofit2.Call;
import retrofit2.http.*;

public interface UsersApi {

    /**
     * User Login
     * The User Login endpoint returns the information associated with the User identified by the e-mail address and password. Users are created if necessary.
     * @return Call<User>
     */

    @GET("login")
    Call<UserModel> loginGet();



}
