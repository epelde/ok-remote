package io.github.epelde.okremote.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by epelde on 7/10/16.
 */
public class ToggleCommand {

    @SerializedName("pID")
    private int parent;

    @SerializedName("cID")
    private int channel;

    @SerializedName("Ctrl")
    private int status;

    public ToggleCommand(Device device) {
        this.parent = device.getParent();
        this.channel = device.getChannel();
        this.status = device.getStatus();
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
