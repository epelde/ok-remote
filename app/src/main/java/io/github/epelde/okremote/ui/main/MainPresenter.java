package io.github.epelde.okremote.ui.main;

import android.util.Log;

import io.github.epelde.okremote.business.CheckStatusInteractor;
import io.github.epelde.okremote.business.LoginInteractor;
import io.github.epelde.okremote.business.ToggleInteractor;
import io.github.epelde.okremote.data.model.DeviceCollection;
import io.github.epelde.okremote.data.model.LoginPermissions;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by epelde on 29/9/16.
 */
public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView view;

    private LoginInteractor loginInteractor;

    private CheckStatusInteractor checkStatusInteractor;

    private ToggleInteractor toggleInteractor;

    private Subscription subscription;

    public MainPresenter(LoginInteractor loginInteractor, CheckStatusInteractor checkStatusInteractor,
                         ToggleInteractor toggleInteractor) {
        this.loginInteractor = loginInteractor;
        this.checkStatusInteractor = checkStatusInteractor;
        this.toggleInteractor = toggleInteractor;
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
        loginInteractor.execute()
                .subscribe(new Action1<LoginPermissions>() {
                    @Override
                    public void call(LoginPermissions response) {
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
                        Log.d("TAG", "* * * LOGIN COMPLETED");
                    }
                });
    }

    @Override
    public void toggle(boolean checked) {
        checkStatusInteractor.execute()
                .subscribe(new Action1<DeviceCollection>() {
                    @Override
                    public void call(DeviceCollection deviceCollection) {
                        Log.d("TAG", "* * * DEVICES: " + deviceCollection.getDevices().size());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("TAG", "* * * ERROR: " + throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        Log.d("TAG", "checkStatusInteractor completed!!!");
                    }
                });
    }

    private void displayStatus() {
        this.view.displayStatus(true);
    }

}
