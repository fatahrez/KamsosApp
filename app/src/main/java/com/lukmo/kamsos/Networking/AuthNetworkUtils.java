package com.lukmo.kamsos.Networking;


public class AuthNetworkUtils {
    private static UserService userService;

    public static UserService postAuthRequest(String authToken) {
        if (userService == null) {
            userService = AuthServiceGenerator.RequestInstance(authToken).create(UserService.class);
        }
        return userService;
    }
}
