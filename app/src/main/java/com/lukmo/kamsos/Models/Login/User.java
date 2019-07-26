package com.lukmo.kamsos.Models.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

    @SerializedName("user")
    @Expose
    private User_ user;

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("user", user).toString();
    }

}
