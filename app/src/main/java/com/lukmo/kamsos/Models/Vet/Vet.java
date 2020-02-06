package com.lukmo.kamsos.Models.Vet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Vet {

    @SerializedName("vet")
    @Expose
    private Vet_ vet;

    public Vet_ getVet() {
        return vet;
    }

    public void setVet(Vet_ vet) {
        this.vet = vet;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("vet", vet).toString();
    }

}

