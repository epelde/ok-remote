package io.github.epelde.okremote.di;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.epelde.okremote.data.network.InMemoryPersistentCookieStore;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by epelde on 29/9/16.
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Singleton
    @Provides
    CookieStore provideCookieStore() {
        return new InMemoryPersistentCookieStore();
    }

    @Singleton
    @Provides
    CookieHandler provideCookieHandler(CookieStore cookieStore) {
        return new CookieManager(cookieStore, CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder,
                             OkHttpClient.Builder client,
                             GsonConverterFactory converterFactory,
                             RxJavaCallAdapterFactory callAdapterFactory,
                             HttpLoggingInterceptor httpLoggingInterceptor,
                             CookieHandler cookieHandler) {
        client.cookieJar(new JavaNetCookieJar(cookieHandler)).addInterceptor(httpLoggingInterceptor);
        return builder.baseUrl("http://46.27.238.138:10000/")
                .client(client.build())
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }
}
