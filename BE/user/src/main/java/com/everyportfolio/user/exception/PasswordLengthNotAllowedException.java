package com.everyportfolio.user.exception;

public class PasswordLengthNotAllowedException extends Exception {
    public PasswordLengthNotAllowedException(String msg) {
        super(msg);
    }
}
