package io.github.epelde.okremote.business;

import io.github.epelde.okremote.data.ApiRepositoy;
import io.github.epelde.okremote.data.model.Device;
import io.github.epelde.okremote.data.model.ToggleCommand;
import io.github.epelde.okremote.data.model.ToggleCommandResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by epelde on 30/9/16.
 */
public class ToggleInteractorImpl implements ToggleInteractor {

    private ApiRepositoy api;

    public ToggleInteractorImpl(ApiRepositoy api) {
        this.api = api;
    }

    @Override
    public Observable<ToggleCommandResponse> execute(Device device) {
        ToggleCommand command = new ToggleCommand();
        command.setChannelId(device.getChannelId());
        command.setParentId(device.getParentId());
        if (device.isChecked()) {
            command.setStatus(1);
        } else {
            command.setStatus(0);
        }
        return api.toggle(command)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }
}
