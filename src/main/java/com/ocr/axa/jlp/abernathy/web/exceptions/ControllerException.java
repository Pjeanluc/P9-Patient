package com.ocr.axa.jlp.abernathy.web.exceptions;

public class ControllerException extends RuntimeException {
    
    public ControllerException(String s) {
        super("ERROR : " + s);
    }
}
