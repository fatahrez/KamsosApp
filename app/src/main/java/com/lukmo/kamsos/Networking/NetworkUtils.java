package com.lukmo.kamsos.Networking;

public class NetworkUtils {
    private static UserService userService;

    public static UserService ApiInstance(String email, String password){
        if (userService == null){
            userService = ServiceGenerator.getUser(email, password).create(UserService.class);
        }
        return userService;
    }
}
