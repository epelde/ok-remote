package io.github.epelde.okremote.data;

import io.github.epelde.okremote.data.model.DeviceCollection;
import io.github.epelde.okremote.data.model.LoginCredentials;
import io.github.epelde.okremote.data.model.LoginPermissions;
import io.github.epelde.okremote.data.model.ToggleCommand;
import io.github.epelde.okremote.data.model.ToggleCommandResponse;
import io.github.epelde.okremote.data.network.ApiService;
import rx.Observable;

/**
 * Created by epelde on 29/9/16.
 */
public class ApiRepositoryImpl implements ApiRepositoy {

    private ApiService api;

    public ApiRepositoryImpl(ApiService api) {
        this.api = api;
    }

    public Observable<LoginPermissions> login(LoginCredentials credentials) {
        return api.login(credentials);
    }

    @Override
    public Observable<DeviceCollection> getStatus() {
        return api.getDeviceStatus();
    }

    @Override
    public Observable<ToggleCommandResponse> toggle(ToggleCommand toggleCommand) {
        return api.toggle(toggleCommand);
    }
}
