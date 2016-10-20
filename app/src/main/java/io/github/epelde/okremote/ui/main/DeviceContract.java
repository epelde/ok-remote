package io.github.epelde.okremote.ui.main;

import io.github.epelde.okremote.ui.presentation.DeviceModel;

/**
 * Created by epelde on 20/10/16.
 */
public interface DeviceContract {

    interface DeviceView {

        void showError();
    }

    interface DevicePresenter {

        void attachView(DeviceContract.DeviceView view);

        void detachView();

        void init(DeviceModel deviceModel);

        void onDeviceClicked(boolean toggle);
    }
}
