package io.github.epelde.okremote.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.epelde.okremote.business.LoginInteractor;
import io.github.epelde.okremote.business.LoginInteractorImpl;
import io.github.epelde.okremote.data.ApiRepositoryImpl;
import io.github.epelde.okremote.data.ApiRepositoy;
import io.github.epelde.okremote.data.ApiService;
import io.github.epelde.okremote.ui.main.MainContract;
import io.github.epelde.okremote.ui.main.MainPresenter;
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
    public MainContract.MainPresenter provideMainPresenter(LoginInteractor loginInteractor) {
        return new MainPresenter(loginInteractor);
    }
}
