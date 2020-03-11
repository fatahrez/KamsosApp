package com.lukmo.kamsos.Callback;

public abstract class VetDetailCallback<T> {
    public abstract void getVetDetail(T t);
    public abstract void returnError(String message);
}
