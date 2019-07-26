package com.lukmo.kamsos.UserInfrastructure;

import com.lukmo.kamsos.Models.Login.User;

import java.util.List;

public interface UserInfrastructure {
    interface View {
        void init();

        void showError(String message);

        void loadDataInList(List<User> users);
    }

    interface Presenter {
        void start();

        void loadUsers();
    }

}
