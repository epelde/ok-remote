package io.github.epelde.okremote.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.epelde.okremote.OkRemoteApp;
import io.github.epelde.okremote.R;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    @Inject
    MainContract.MainPresenter presenter;

    @BindView(R.id.light_switch)
    SwitchCompat lightSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((OkRemoteApp)getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        presenter.attachView(this);
        presenter.init();
        initViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && presenter != null) {
            presenter.detachView();
        }
    }

    @OnClick(R.id.light_switch)
    public void toggle() {
        presenter.toggle(lightSwitch.isChecked());
    }

    @Override
    public void displayStatus(boolean checked) {
        lightSwitch.setEnabled(true);
        lightSwitch.setChecked(checked);
    }

    private void initViews() {
        lightSwitch.setEnabled(false);
    }
}
