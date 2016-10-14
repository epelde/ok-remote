package io.github.epelde.okremote.ui.main;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.epelde.okremote.R;
import io.github.epelde.okremote.data.model.Device;

/**
 * Created by epelde on 14/10/16.
 */
public class DevicesAdapter extends ArrayAdapter<Device> {

    private List<Device> items;

    public DevicesAdapter(Context context, List<Device> items) {
        super(context, 0, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Device item = items.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.label.setText(item.getName());
        if (item.getStatus() == 1) {
            viewHolder.toggle.setChecked(true);
        } else {
            viewHolder.toggle.setChecked(false);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.text_device_label)
        TextView label;

        @BindView(R.id.switch_device)
        SwitchCompat toggle;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
