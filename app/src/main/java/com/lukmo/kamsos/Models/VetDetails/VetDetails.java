package com.lukmo.kamsos.Models.VetDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class VetDetails {

    @SerializedName("vet")
    @Expose
    private Vet vet;

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("vet", vet).toString();
    }

}
