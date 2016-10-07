package io.github.epelde.okremote.business;

import io.github.epelde.okremote.data.model.Device;
import io.github.epelde.okremote.data.model.DeviceCollection;
import rx.Observable;

/**
 * Created by epelde on 7/10/16.
 */
public interface CheckStatusInteractor {

    Observable<DeviceCollection> execute();
}
