package io.github.epelde.okremote.ui.main;

import android.util.Log;

import io.github.epelde.okremote.business.LoginInteractor;
import io.github.epelde.okremote.data.model.LoginResponse;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by epelde on 29/9/16.
 */
public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView view;

    private LoginInteractor loginInteractor;

    private Subscription subscription;

    public MainPresenter(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void attachView(MainContract.MainView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void init() {
        subscription = loginInteractor.login()
                .subscribe(new Action1<LoginResponse>() {
                    @Override
                    public void call(LoginResponse response) {
                        Log.d("TAG", "* * * LOGIN:" + response.getPermissions());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("TAG", "* * * ERROR:" + throwable.toString());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        Log.d("TAG", "* * * COMPLETED");
                        renderText("COMPLETED");
                    }
                });
    }

    private void renderText(String text) {
        this.view.renderText(text);
    }
}
