package io.github.epelde.okremote.data;

import io.github.epelde.okremote.data.model.Login;
import io.github.epelde.okremote.data.model.LoginResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by epelde on 29/9/16.
 */
public interface ApiService {

    @POST("/rs/login")
    Observable<LoginResponse> login(@Body Login login);
}
