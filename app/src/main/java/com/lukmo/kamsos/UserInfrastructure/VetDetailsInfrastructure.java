package com.lukmo.kamsos.UserInfrastructure;

public interface VetDetailsInfrastructure {
    interface View {
        void init();

        void loadDataInVetDetail();
    }

    interface Presenter {
        void start();

        void loadVetDetails();
    }
}
