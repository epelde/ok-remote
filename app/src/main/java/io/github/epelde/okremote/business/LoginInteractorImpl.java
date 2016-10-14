package io.github.epelde.okremote.business;

import io.github.epelde.okremote.business.LoginInteractor;
import io.github.epelde.okremote.data.ApiRepositoy;
import io.github.epelde.okremote.data.model.LoginCredentials;
import io.github.epelde.okremote.data.model.LoginPermissions;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by epelde on 29/9/16.
 */
public class LoginInteractorImpl implements LoginInteractor {

    private ApiRepositoy api;

    public LoginInteractorImpl(ApiRepositoy api) {
        this.api = api;
    }

    @Override
    public Observable<LoginPermissions> execute() {
        return this.api.login(new LoginCredentials("admin", "admin"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
