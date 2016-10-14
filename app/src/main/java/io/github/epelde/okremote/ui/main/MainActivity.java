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
import io.github.epelde.okremote.data.model.Device;

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
        presenter.init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            presenter.detachView();
        }
    }

    public void toggle() {
        //presenter.toggle(lightSwitch.isChecked());
    }

    @Override
    public void displayStatus(List<Device> devices) {
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.item_layout, devices);
        listView.setAdapter(adapter);
    }
}
