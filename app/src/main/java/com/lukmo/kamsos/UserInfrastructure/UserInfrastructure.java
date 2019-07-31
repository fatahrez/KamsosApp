package com.lukmo.kamsos.UserInfrastructure;

import com.lukmo.kamsos.Models.Login.User;
import com.lukmo.kamsos.Models.Vet.Vet;

import java.util.List;

public interface UserInfrastructure {
    interface View {
        void init();

        void showError(String message);

        void loadDataInList(List<Vet> vets);
    }

    interface Presenter {
        void start();

        void loadVets();
    }

}
