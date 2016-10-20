package io.github.epelde.okremote.business;

import io.github.epelde.okremote.data.ApiRepositoy;
import io.github.epelde.okremote.data.entity.Device;
import io.github.epelde.okremote.data.entity.ToggleCommand;
import io.github.epelde.okremote.data.entity.ToggleCommandResponse;
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
        ToggleCommand command = new ToggleCommand(device);
        return api.toggle(command)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }
}
