package io.github.epelde.okremote.ui.main;

import android.support.annotation.NonNull;
import android.util.Log;

import io.github.epelde.okremote.business.ToggleInteractor;
import io.github.epelde.okremote.business.util.RetryCookieSession;
import io.github.epelde.okremote.data.entity.ToggleCommandResponse;
import io.github.epelde.okremote.ui.main.mapper.DeviceModelMapper;
import io.github.epelde.okremote.ui.presentation.DeviceModel;
import rx.functions.Action1;

/**
 * Created by epelde on 20/10/16.
 */
public class DevicePresenter implements DeviceContract.DevicePresenter {

    private DeviceContract.DeviceView view;

    private ToggleInteractor toggleInteractor;

    private RetryCookieSession retryCookieSession;

    private DeviceModelMapper mapper;

    private DeviceModel deviceModel;

    public DevicePresenter(ToggleInteractor toggleInteractor, RetryCookieSession retryCookieSession,
                           DeviceModelMapper mapper) {
        this.toggleInteractor = toggleInteractor;
        this.retryCookieSession = retryCookieSession;
        this.mapper = mapper;
    }

    @Override
    public void attachView(@NonNull DeviceContract.DeviceView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void init(@NonNull DeviceModel deviceModel) {
        this.deviceModel = deviceModel;
    }

    @Override
    public void onDeviceClicked(boolean toggle) {
        Log.d("TAG", "* * * TOGGLE STATUS:" + toggle);
        this.deviceModel.setStatus(toggle);
        // response { "FeedBackStatus": 1 } or { "FeedBackStatus": 0 }
        toggleInteractor.execute(mapper.transform(this.deviceModel))
            .retryWhen(retryCookieSession)
            .subscribe(new Action1<ToggleCommandResponse>() {
                @Override
                public void call(ToggleCommandResponse toggleCommandResponse) {

                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Log.d("TAG", "* * * ERROR: " + throwable.getMessage());
                    showError();
                }
            });
    }

    private void showError() {
        Log.d("TAG", "* * * showError CURRENT STATUS" + deviceModel.isStatus());
        deviceModel.revertStatus();
    }
}
