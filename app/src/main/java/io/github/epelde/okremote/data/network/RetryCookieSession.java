package io.github.epelde.okremote.data.network;

import io.github.epelde.okremote.business.LoginInteractor;
import io.github.epelde.okremote.data.model.LoginPermissions;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by epelde on 7/10/16.
 */
public class RetryCookieSession implements Func1<Observable<? extends Throwable>, Observable<LoginPermissions>> {

    private LoginInteractor loginInteractor;

    public RetryCookieSession(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    @Override
    public Observable<LoginPermissions> call(Observable<? extends Throwable> throwable) {
        return throwable.flatMap(new Func1<Throwable, Observable<LoginPermissions>>() {
            @Override
            public Observable<LoginPermissions> call(Throwable throwable) {
                return loginInteractor.execute();
            }
        });
    }
}
