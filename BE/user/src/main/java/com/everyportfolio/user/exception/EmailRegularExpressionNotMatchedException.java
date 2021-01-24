package com.everyportfolio.user.exception;

public class EmailRegularExpressionNotMatchedException extends Exception {
    public EmailRegularExpressionNotMatchedException() {

    }

    public  EmailRegularExpressionNotMatchedException(String msg) {
        super(msg);
    }
}
