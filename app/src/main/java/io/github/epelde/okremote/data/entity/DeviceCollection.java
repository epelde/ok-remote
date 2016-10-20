package io.github.epelde.okremote.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by epelde on 6/10/16.
 */
public class DeviceCollection {

    @SerializedName("SmartLinkAuxiliarys")
    private List<Device> devices;

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
