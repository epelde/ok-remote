package io.github.epelde.okremote.ui.main;

/**
 * Created by epelde on 29/9/16.
 */
public interface MainContract {

    interface MainView {

        void renderText(String text);
    }

    interface MainPresenter {

        void attachView(MainContract.MainView view);

        void detachView();

        void init();
    }
}
