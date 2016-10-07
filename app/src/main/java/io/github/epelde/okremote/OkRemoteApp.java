package io.github.epelde.okremote;

import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

import io.github.epelde.okremote.business.LoginInteractor;
import io.github.epelde.okremote.data.model.LoginPermissions;
import io.github.epelde.okremote.di.ApplicationComponent;
import io.github.epelde.okremote.di.DaggerApplicationComponent;
import rx.functions.Action1;

/**
 * Created by epelde on 29/9/16.
 */
public class OkRemoteApp extends Application {

    private ApplicationComponent applicationComponent;

    @Inject
    LoginInteractor loginInteractor;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().build();
        applicationComponent.inject(this);
        loginInteractor.execute()
                .subscribe(new Action1<LoginPermissions>() {
                    @Override
                    public void call(LoginPermissions response) {
                        Log.d("TAG", "* * * LOGIN PERMISSIONS:" + response.getPermissions());
                    }
                });
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
