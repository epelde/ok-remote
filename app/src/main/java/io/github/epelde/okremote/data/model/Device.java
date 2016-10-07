package io.github.epelde.okremote.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by epelde on 6/10/16.
 */
public class Device {

    @SerializedName("Label")
    private String label;

    @SerializedName("Name")
    private String name;

    @SerializedName("Parent_ID")
    private int parentId;

    @SerializedName("Channel_ID")
    private int channelId;

    @SerializedName("Status")
    private int status;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }
}
