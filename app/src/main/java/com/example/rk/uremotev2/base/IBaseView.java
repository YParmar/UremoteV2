package com.example.rk.uremotev2.base;

public interface IBaseView {

    void showMessage(String message);

    void showLongMessage(String message);

    void finishActivityAndStartAnotherActivity(Class activityClass);

    void hideKeyboard();
}
