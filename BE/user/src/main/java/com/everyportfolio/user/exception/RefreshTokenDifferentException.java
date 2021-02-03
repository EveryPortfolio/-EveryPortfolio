package com.everyportfolio.user.exception;

public class RefreshTokenDifferentException extends Exception {
    public RefreshTokenDifferentException() {

    }

    public RefreshTokenDifferentException(String msg) {
        super(msg);
    }
}
