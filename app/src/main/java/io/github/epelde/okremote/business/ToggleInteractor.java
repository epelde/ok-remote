package io.github.epelde.okremote.business;

import io.github.epelde.okremote.data.model.Device;
import io.github.epelde.okremote.data.model.DeviceCollection;
import rx.Observable;

/**
 * Created by epelde on 30/9/16.
 */
public interface ToggleInteractor {

    Observable<Device> execute();
}
