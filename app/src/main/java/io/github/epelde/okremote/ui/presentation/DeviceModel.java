package io.github.epelde.okremote.ui.presentation;

import io.github.epelde.okremote.data.entity.Device;

/**
 * Created by epelde on 20/10/16.
 */
public class DeviceModel {

    private String label;

    private String name;

    private int parent;

    private int channel;

    private boolean status;

    public DeviceModel(Device device) {
        this.label = device.getLabel();
        this.name = device.getName();
        this.parent = device.getParent();
        this.channel = device.getChannel();
        if (device.getStatus() == 1) {
            this.status = true;
        } else {
            this.status = false;
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
