package io.github.epelde.okremote.business;

import io.github.epelde.okremote.data.entity.LoginPermissions;
import rx.Observable;

/**
 * Created by epelde on 29/9/16.
 */
public interface LoginInteractor {

    Observable<LoginPermissions> execute();
}
