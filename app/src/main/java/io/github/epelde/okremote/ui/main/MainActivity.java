package io.github.epelde.okremote.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.epelde.okremote.OkRemoteApp;
import io.github.epelde.okremote.R;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    @Inject
    MainContract.MainPresenter presenter;
    @BindView(R.id.textview_text)
    TextView text;

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
        if (isFinishing() && presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public void renderText(String text) {
        this.text.setText(text);
    }
}
