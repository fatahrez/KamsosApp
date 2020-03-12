package com.lukmo.kamsos.Models.RequestVet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RequestVet {
    public class VetRequest {

        @SerializedName("vet_request")
        @Expose
        private VetRequest_ vetRequest;

        public VetRequest_ getVetRequest() {
            return vetRequest;
        }

        public void setVetRequest(VetRequest_ vetRequest) {
            this.vetRequest = vetRequest;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("vetRequest", vetRequest).toString();
        }

    }
}
