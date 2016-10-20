package io.github.epelde.okremote.data.network;

import io.github.epelde.okremote.data.entity.DeviceCollection;
import io.github.epelde.okremote.data.entity.LoginCredentials;
import io.github.epelde.okremote.data.entity.LoginPermissions;
import io.github.epelde.okremote.data.entity.ToggleCommand;
import io.github.epelde.okremote.data.entity.ToggleCommandResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by epelde on 29/9/16.
 */
public interface ApiService {

    @POST("/rs/login")
    Observable<LoginPermissions> login(@Body LoginCredentials login);

    @GET("/slip/SmartLinkIPAuxiliary")
    Observable<DeviceCollection> getDeviceStatus();

    @POST("/slip/Firmware/methods/operate")
    Observable<ToggleCommandResponse> toggle(@Body ToggleCommand command);
}
