package com.everyportfolio.user.exception;

public class EmailAuthenticationFailedException extends Exception {

    public EmailAuthenticationFailedException() {

    }

    public EmailAuthenticationFailedException(String msg){
        super(msg);
    }
}
