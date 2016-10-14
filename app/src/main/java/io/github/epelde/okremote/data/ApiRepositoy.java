package io.github.epelde.okremote.data;

import io.github.epelde.okremote.data.model.DeviceCollection;
import io.github.epelde.okremote.data.model.LoginCredentials;
import io.github.epelde.okremote.data.model.LoginPermissions;
import io.github.epelde.okremote.data.model.ToggleCommand;
import io.github.epelde.okremote.data.model.ToggleCommandResponse;
import rx.Observable;

/**
 * Created by epelde on 29/9/16.
 */
public interface ApiRepositoy {

    Observable<LoginPermissions> login(LoginCredentials credentials);

    Observable<DeviceCollection> getStatus();

    Observable<ToggleCommandResponse> toggle(ToggleCommand toggleCommand);
}
