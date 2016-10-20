package io.github.epelde.okremote.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by epelde on 7/10/16.
 */
public class ToggleCommandResponse {

    @SerializedName("FeedBackStatus")
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
