package io.github.epelde.okremote.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import io.github.epelde.okremote.OkRemoteApp;
import io.github.epelde.okremote.ui.main.MainActivity;
import io.github.epelde.okremote.ui.main.adapter.DevicesAdapter;

/**
 * Created by epelde on 29/9/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);

    void inject(DevicesAdapter.ViewHolder target);
}
