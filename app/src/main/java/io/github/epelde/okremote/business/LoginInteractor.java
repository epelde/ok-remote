package io.github.epelde.okremote.business;

import io.github.epelde.okremote.data.model.Login;
import io.github.epelde.okremote.data.model.LoginResponse;
import rx.Observable;

/**
 * Created by epelde on 29/9/16.
 */
public interface LoginInteractor {

    Observable<LoginResponse> login();
}
