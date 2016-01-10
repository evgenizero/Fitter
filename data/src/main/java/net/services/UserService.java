package net.services;

import entities.UserEntity;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by evgeniy on 1/9/16.
 */
public interface UserService {

    @GET("users/register")
    Observable<UserEntity> registerUser();

}
