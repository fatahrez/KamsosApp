package com.lukmo.kamsos.Models.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoginResponse {

    @SerializedName("user")
    @Expose
    private LoginResponse_ user;

    public LoginResponse_ getUser() {
        return user;
    }

    public void setUser(LoginResponse_ user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("user", user).toString();
    }

}
