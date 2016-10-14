package io.github.epelde.okremote.business;

import io.github.epelde.okremote.business.CheckStatusInteractor;
import io.github.epelde.okremote.data.ApiRepositoy;
import io.github.epelde.okremote.data.model.Device;
import io.github.epelde.okremote.data.model.DeviceCollection;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by epelde on 7/10/16.
 */
public class CheckStatusInteractorImpl implements io.github.epelde.okremote.business.CheckStatusInteractor {

    private ApiRepositoy api;

    public CheckStatusInteractorImpl(ApiRepositoy api) {
        this.api = api;
    }

    @Override
    public Observable<DeviceCollection> execute() {
        return api.getStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
