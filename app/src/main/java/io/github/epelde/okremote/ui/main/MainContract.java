package io.github.epelde.okremote.ui.main;

/**
 * Created by epelde on 29/9/16.
 */
public interface MainContract {

    interface MainView {

        void displayStatus(boolean checked);
    }

    interface MainPresenter {

        void attachView(MainContract.MainView view);

        void detachView();

        void init();

        void toggle(boolean checked);
    }
}
