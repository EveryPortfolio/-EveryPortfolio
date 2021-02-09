package com.everyportfolio.portfolio.exception;

public class EmailRegularExpressionNotMatchedException extends Exception {
    public EmailRegularExpressionNotMatchedException() {}
    public EmailRegularExpressionNotMatchedException(String msg) {
        super(msg);
    }
}
