package io.github.epelde.okremote.data;

import io.github.epelde.okremote.data.model.Login;
import io.github.epelde.okremote.data.model.LoginResponse;
import rx.Observable;

/**
 * Created by epelde on 29/9/16.
 */
public class ApiRepositoryImpl implements ApiRepositoy {

    private ApiService apiService;

    public ApiRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<LoginResponse> getData() {
        return apiService.login(new Login("admin", "admin"));
    }
}
