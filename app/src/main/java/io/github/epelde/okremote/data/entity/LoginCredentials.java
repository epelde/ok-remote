package io.github.epelde.okremote.data.entity;

/**
 * Created by epelde on 29/9/16.
 */
public class LoginCredentials {

    private String username;

    private String password;

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
}
