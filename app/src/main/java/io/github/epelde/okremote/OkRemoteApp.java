package io.github.epelde.okremote;

import android.app.Application;

import io.github.epelde.okremote.di.ApplicationComponent;
import io.github.epelde.okremote.di.DaggerApplicationComponent;

/**
 * Created by epelde on 29/9/16.
 */
public class OkRemoteApp extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
