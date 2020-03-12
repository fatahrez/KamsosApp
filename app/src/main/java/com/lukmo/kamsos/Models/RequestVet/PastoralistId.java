package com.lukmo.kamsos.Models.RequestVet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PastoralistId {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("image")
    @Expose
    private Object image;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("username", username).append("image", image).toString();
    }
}
