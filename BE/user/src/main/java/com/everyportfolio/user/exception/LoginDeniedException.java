package com.everyportfolio.user.exception;

public class LoginDeniedException extends Exception {
    public LoginDeniedException() {

    }

    public LoginDeniedException(String msg) {
        super(msg);
    }
}
