package io.github.epelde.okremote.business;

import javax.inject.Inject;

import io.github.epelde.okremote.data.ApiRepositoryImpl;
import io.github.epelde.okremote.data.ApiRepositoy;
import io.github.epelde.okremote.data.model.LoginResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by epelde on 29/9/16.
 */
public class LoginInteractorImpl implements LoginInteractor {

    private ApiRepositoy apiRepositoy;

    @Inject
    public LoginInteractorImpl(ApiRepositoy apiRepositoy) {
        this.apiRepositoy = apiRepositoy;
    }

    @Override
    public Observable<LoginResponse> login() {
        return this.apiRepositoy.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
