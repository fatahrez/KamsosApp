package com.lukmo.kamsos.Models.RequestVet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class VetRequest_ {
    @SerializedName("pastoralist_id")
    @Expose
    private PastoralistId pastoralistId;
    @SerializedName("vet_id")
    @Expose
    private Integer vetId;

    public PastoralistId getPastoralistId() {
        return pastoralistId;
    }

    public void setPastoralistId(PastoralistId pastoralistId) {
        this.pastoralistId = pastoralistId;
    }

    public Integer getVetId() {
        return vetId;
    }

    public void setVetId(Integer vetId) {
        this.vetId = vetId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("pastoralistId", pastoralistId).append("vetId", vetId).toString();
    }
}
