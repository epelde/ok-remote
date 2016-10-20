package io.github.epelde.okremote.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.epelde.okremote.business.CheckStatusInteractor;
import io.github.epelde.okremote.business.CheckStatusInteractorImpl;
import io.github.epelde.okremote.business.LoginInteractor;
import io.github.epelde.okremote.business.LoginInteractorImpl;
import io.github.epelde.okremote.business.ToggleInteractor;
import io.github.epelde.okremote.business.ToggleInteractorImpl;
import io.github.epelde.okremote.business.util.RetryCookieSession;
import io.github.epelde.okremote.data.ApiRepositoryImpl;
import io.github.epelde.okremote.data.ApiRepositoy;
import io.github.epelde.okremote.data.network.ApiService;
import io.github.epelde.okremote.ui.main.DeviceContract;
import io.github.epelde.okremote.ui.main.DevicePresenter;
import io.github.epelde.okremote.ui.main.MainContract;
import io.github.epelde.okremote.ui.main.MainPresenter;
import io.github.epelde.okremote.ui.main.mapper.DeviceModelMapper;
import retrofit2.Retrofit;

/**
 * Created by epelde on 29/9/16.
 */
@Module
public class ApplicationModule {

    @Singleton
    @Provides
    ApiService provideAPIService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    ApiRepositoy provideApiRepository(ApiService apiService) {
        return new ApiRepositoryImpl(apiService);
    }

    @Singleton
    @Provides
    LoginInteractor provideLoginInteractor(ApiRepositoy apiRepositoy) {
        return new LoginInteractorImpl(apiRepositoy);
    }

    @Singleton
    @Provides
    CheckStatusInteractor provideCheckStatusInteractor(ApiRepositoy apiRepositoy) {
        return new CheckStatusInteractorImpl(apiRepositoy);
    }

    @Singleton
    @Provides
    ToggleInteractor provideToggleInteractor(ApiRepositoy apiRepositoy) {
        return new ToggleInteractorImpl(apiRepositoy);
    }

    @Singleton
    @Provides
    public MainContract.MainPresenter provideMainPresenter(CheckStatusInteractor interactor,
                                                           RetryCookieSession retryCookieSession,
                                                           DeviceModelMapper mapper) {
        return new MainPresenter(interactor, retryCookieSession, mapper);
    }

    @Provides
    public DeviceContract.DevicePresenter provideDevicePresenter(ToggleInteractor interactor,
                                                                 RetryCookieSession retryCookieSession,
                                                                 DeviceModelMapper mapper) {
        return new DevicePresenter(interactor, retryCookieSession, mapper);
    }
}
