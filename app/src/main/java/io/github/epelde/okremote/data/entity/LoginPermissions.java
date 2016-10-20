package io.github.epelde.okremote.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by epelde on 29/9/16.
 */
public class LoginPermissions {

    @SerializedName("Permissions")
    private String permissions;

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
