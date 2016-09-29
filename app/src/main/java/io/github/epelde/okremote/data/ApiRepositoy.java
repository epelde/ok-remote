package io.github.epelde.okremote.data;

import io.github.epelde.okremote.data.model.LoginResponse;
import rx.Observable;

/**
 * Created by epelde on 29/9/16.
 */
public interface ApiRepositoy {

    Observable<LoginResponse> getData();
}
