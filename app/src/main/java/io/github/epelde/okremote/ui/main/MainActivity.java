package io.github.epelde.okremote.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.epelde.okremote.OkRemoteApp;
import io.github.epelde.okremote.R;
import io.github.epelde.okremote.ui.main.adapter.DevicesAdapter;
import io.github.epelde.okremote.ui.presentation.DeviceModel;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    @Inject
    MainContract.MainPresenter presenter;

    @BindView(R.id.list_view)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((OkRemoteApp)getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        presenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            presenter.detachView();
        }
    }

    @Override
    public void displayStatus(List<DeviceModel> devices) {
        listView.setAdapter(new DevicesAdapter(this, devices));
    }
}
