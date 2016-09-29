package io.github.epelde.okremote.di;

import javax.inject.Singleton;

import dagger.Component;
import io.github.epelde.okremote.ui.main.MainActivity;

/**
 * Created by epelde on 29/9/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(MainActivity target);
}
