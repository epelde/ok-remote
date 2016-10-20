package io.github.epelde.okremote.ui.main.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.epelde.okremote.data.entity.Device;
import io.github.epelde.okremote.ui.presentation.DeviceModel;

/**
 * Created by epelde on 20/10/16.
 */
@Singleton
public class DeviceModelMapper {

    @Inject
    public DeviceModelMapper() {

    }

    public DeviceModel transform(Device device) {
        return new DeviceModel(device);
    }

    public List<DeviceModel> transform(List<Device> devices) {
        List<DeviceModel> list;
        if (devices != null && !devices.isEmpty()) {
            list = new ArrayList<>();
            for (Device device : devices) {
                list.add(transform(device));
            }
        } else {
            list = Collections.emptyList();
        }
        return list;
    }

    public Device transform(DeviceModel model) {
        return new Device(model);
    }

}
