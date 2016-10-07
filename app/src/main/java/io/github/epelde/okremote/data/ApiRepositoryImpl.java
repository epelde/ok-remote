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

    public Observable<LoginPermissions> login() {
        return api.login(new LoginCredentials("admin", "admin"));
    }

    @Override
    public Observable<DeviceCollection> getStatus() {
        return api.getDeviceStatus();
    }

    @Override
    public Observable<ToggleCommandResponse> toggle(boolean checked) {
        ToggleCommand command = new ToggleCommand();
        command.setChannelId(2);
        command.setParentId(0);
        if (checked) {
            command.setStatus(1);
        } else {
            command.setStatus(0);
        }
        return api.toggle(command);
    }
}
