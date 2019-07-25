package com.lukmo.kamsos.Models.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Register {

    @SerializedName("user")
    @Expose
    private Register_ user;

    public Register_ getUser() {
        return user;
    }

    public void setUser(Register_ user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("user", user).toString();
    }

}

