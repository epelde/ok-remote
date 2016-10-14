package io.github.epelde.okremote.ui.main;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.github.epelde.okremote.business.CheckStatusInteractor;
import io.github.epelde.okremote.business.util.RetryCookieSession;
import io.github.epelde.okremote.data.model.Device;
import io.github.epelde.okremote.data.model.DeviceCollection;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by epelde on 29/9/16.
 */
public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView view;

    private CheckStatusInteractor checkStatusInteractor;

    private RetryCookieSession retryCookieSession;

    private Subscription subscription;

    @Inject
    public MainPresenter(CheckStatusInteractor checkStatusInteractor,
                         RetryCookieSession retryCookieSession) {
        this.checkStatusInteractor = checkStatusInteractor;
        this.retryCookieSession = retryCookieSession;
    }

    @Override
    public void attachView(MainContract.MainView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void init() {
        checkStatusInteractor.execute()
                .retryWhen(retryCookieSession)
                .subscribe(new Action1<DeviceCollection>() {
                    @Override
                    public void call(DeviceCollection deviceCollection) {
                        displayStatus(deviceCollection.getDevices());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("TAG", "* * * ERROR: " + throwable.getMessage());
                    }
                });
    }

    private void displayStatus(List<Device> devices) {
        this.view.displayStatus(devices);
    }
}
