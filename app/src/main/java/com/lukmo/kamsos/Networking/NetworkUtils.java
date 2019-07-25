package com.lukmo.kamsos.Networking;

public class NetworkUtils {
    private static UserService userService;

    public static UserService ApiInstance(){
        if (userService == null){
            userService = ServiceGenerator.getUser().create(UserService.class);
        }
        return userService;
    }
}
