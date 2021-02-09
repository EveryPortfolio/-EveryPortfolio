package com.everyportfolio.portfolio.exception;

public class TooLongThumbnailURLException extends Exception {
    public TooLongThumbnailURLException() {
    }

    public TooLongThumbnailURLException(String msg) {
        super(msg);
    }
}
