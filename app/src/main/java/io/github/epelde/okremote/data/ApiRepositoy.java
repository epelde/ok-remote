package io.github.epelde.okremote.data;

import io.github.epelde.okremote.data.entity.DeviceCollection;
import io.github.epelde.okremote.data.entity.LoginCredentials;
import io.github.epelde.okremote.data.entity.LoginPermissions;
import io.github.epelde.okremote.data.entity.ToggleCommand;
import io.github.epelde.okremote.data.entity.ToggleCommandResponse;
import rx.Observable;

/**
 * Created by epelde on 29/9/16.
 */
public interface ApiRepositoy {

    Observable<LoginPermissions> login(LoginCredentials credentials);

    Observable<DeviceCollection> getStatus();

    Observable<ToggleCommandResponse> toggle(ToggleCommand toggleCommand);
}
