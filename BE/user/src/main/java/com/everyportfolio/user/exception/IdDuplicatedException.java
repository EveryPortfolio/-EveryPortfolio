package com.everyportfolio.user.exception;

public class IdDuplicatedException extends Exception {
    public IdDuplicatedException() {

    }

    public IdDuplicatedException(String msg) {
        super(msg);
    }
}
