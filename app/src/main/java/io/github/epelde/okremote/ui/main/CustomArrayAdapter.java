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

import io.github.epelde.okremote.R;
import io.github.epelde.okremote.data.model.Device;

/**
 * Created by epelde on 14/10/16.
 */
public class CustomArrayAdapter extends ArrayAdapter<Device> {

    private Context context;

    private int resource;

    private List<Device> items;

    public CustomArrayAdapter(Context context, int resource, List<Device> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(resource, null);
        Device item = items.get(position);
        if (item != null) {
            TextView label = (TextView) v.findViewById(R.id.text_device_label);
            label.setText(item.getLabel());
            SwitchCompat toggle = (SwitchCompat) v.findViewById(R.id.switch_device);
            if (item.getStatus() == 1) {
                toggle.setChecked(true);
            } else {
                toggle.setChecked(false);
            }
        }
        return v;
    }
}
