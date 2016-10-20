package io.github.epelde.okremote.ui.main;

import java.util.Collection;
import java.util.List;

import io.github.epelde.okremote.ui.main.entity.DeviceModel;

/**
 * Created by epelde on 29/9/16.
 */
public interface MainContract {

    interface MainView {

        void displayStatus(List<DeviceModel> devices);
    }

    interface MainPresenter {

        void attachView(MainContract.MainView view);

        void detachView();

        void init();
    }
}
