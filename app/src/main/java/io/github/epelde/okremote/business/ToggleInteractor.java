package io.github.epelde.okremote.business;

import io.github.epelde.okremote.data.entity.Device;
import io.github.epelde.okremote.data.entity.ToggleCommandResponse;
import rx.Observable;

/**
 * Created by epelde on 30/9/16.
 */
public interface ToggleInteractor {

    Observable<ToggleCommandResponse> execute(Device device);
}
