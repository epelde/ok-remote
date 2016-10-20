package io.github.epelde.okremote.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.epelde.okremote.R;
import io.github.epelde.okremote.di.DaggerApplicationComponent;
import io.github.epelde.okremote.ui.main.DeviceContract;
import io.github.epelde.okremote.ui.presentation.DeviceModel;

/**
 * Created by epelde on 14/10/16.
 */
public class DevicesAdapter extends ArrayAdapter<DeviceModel> {

    private List<DeviceModel> items;

    public DevicesAdapter(Context context, List<DeviceModel> items) {
        super(context, 0, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.bind(items.get(position));
        return convertView;
    }

    public static class ViewHolder implements DeviceContract.DeviceView {

        @BindView(R.id.text_device_label)
        TextView label;

        @BindView(R.id.switch_device)
        SwitchCompat switchCompat;

        @Inject
        DeviceContract.DevicePresenter presenter;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            DaggerApplicationComponent.builder().build().inject(this);
            presenter.attachView(this);
        }

        public void bind(final DeviceModel device) {
            presenter.init(device);
            label.setText(device.getName());
            switchCompat.setChecked(device.isStatus());
            switchCompat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.onDeviceClicked(switchCompat.isChecked());
                }
            });
        }

        @Override
        public void showError() {

        }
    }
}
