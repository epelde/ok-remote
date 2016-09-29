package io.github.epelde.okremote.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by epelde on 29/9/16.
 */
public class LoginResponse {

    @SerializedName("UserName")
    private String username;

    @SerializedName("Password")
    private String password;

    @SerializedName("Permissions")
    private String permissions;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
