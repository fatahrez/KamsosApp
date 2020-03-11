package com.lukmo.kamsos.UserInfrastructure;

import com.lukmo.kamsos.Models.VetDetails.VetDetails;

public interface VetDetailsInfrastructure {
    interface View {
        void init();

        void loadDataInVetDetail(VetDetails vetDetails);

        String getSlug();
    }

    interface Presenter {
        void start();

        void loadVetDetails();
    }
}
