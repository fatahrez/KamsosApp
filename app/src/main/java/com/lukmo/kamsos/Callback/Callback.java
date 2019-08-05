package com.lukmo.kamsos.Callback;

public abstract class Callback<T> {
    public abstract void getData(T t);
    public abstract void returnError(String message);
}
