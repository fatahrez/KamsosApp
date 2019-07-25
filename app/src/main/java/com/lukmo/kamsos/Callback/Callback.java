package com.lukmo.kamsos.Callback;

public abstract class Callback<T> {
    public abstract void postData(T t);
    public abstract void returnError(String message);
}
