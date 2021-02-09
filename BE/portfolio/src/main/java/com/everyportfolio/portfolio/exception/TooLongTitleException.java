package com.everyportfolio.portfolio.exception;

public class TooLongTitleException extends Exception {
    public TooLongTitleException() {}

    public TooLongTitleException(String msg) {
        super(msg);
    }
}
