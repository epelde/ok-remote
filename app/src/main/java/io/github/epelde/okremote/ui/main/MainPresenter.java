package io.github.epelde.okremote.ui.main;

import android.util.Log;

import io.github.epelde.okremote.business.CheckStatusInteractor;
import io.github.epelde.okremote.business.ToggleInteractor;
import io.github.epelde.okremote.data.model.Device;
import io.github.epelde.okremote.data.model.DeviceCollection;
import io.github.epelde.okremote.data.model.ToggleCommandResponse;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by epelde on 29/9/16.
 */
public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView view;

    private CheckStatusInteractor checkStatusInteractor;

    private ToggleInteractor toggleInteractor;

    private Subscription subscription;

    public MainPresenter(CheckStatusInteractor checkStatusInteractor,
                         ToggleInteractor toggleInteractor) {
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
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void init() {
        checkStatusInteractor.execute()
                .subscribe(new Action1<DeviceCollection>() {
                    @Override
                    public void call(DeviceCollection deviceCollection) {
                        for (Device device : deviceCollection.getDevices()) {
                            Log.d("TAG", "* * * DEVICE:" + device.getLabel() + "/" +
                                    device.getChannelId() + "/" + device.getParentId());
                        }
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

    @Override
    public void toggle(boolean checked) {
        toggleInteractor.execute(checked).subscribe(new Action1<ToggleCommandResponse>() {
            @Override
            public void call(ToggleCommandResponse commandResponse) {
                Log.d("TAG", "* * * STATUS:" + commandResponse.getStatus());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                Log.d("TAG", "* * * TOGGLE COMPLETED");
            }
        });
    }

    private void displayStatus() {
        this.view.displayStatus(true);
    }

}
