package com.kodilla.ecommercee.controller;

public class UserNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "User not found!";
    }
}
