package io.github.epelde.okremote.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by epelde on 7/10/16.
 */
public class ToggleCommand {

    @SerializedName("pID")
    private int parentId;

    @SerializedName("cID")
    private int channelId;

    @SerializedName("Ctrl")
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
