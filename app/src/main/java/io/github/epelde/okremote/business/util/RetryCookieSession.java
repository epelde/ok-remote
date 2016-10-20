package io.github.epelde.okremote.business.util;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.epelde.okremote.business.LoginInteractor;
import io.github.epelde.okremote.data.entity.LoginPermissions;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by epelde on 7/10/16.
 */
@Singleton
public class RetryCookieSession implements Func1<Observable<? extends Throwable>, Observable<LoginPermissions>> {

    private LoginInteractor loginInteractor;

    @Inject
    public RetryCookieSession(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    @Override
    public Observable<LoginPermissions> call(Observable<? extends Throwable> throwable) {
        return throwable.flatMap(new Func1<Throwable, Observable<LoginPermissions>>() {
            @Override
            public Observable<LoginPermissions> call(Throwable throwable) {
                // TODO We should check something about this Throwable before
                // TODO executing the interactor. Probably we are interested in 401 error codes.
                return loginInteractor.execute();
            }
        });
    }
}
