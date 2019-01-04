package com.petrego.domain;

public class PetRegoException extends Exception {

    private MessageCode messageCode;
    private String message;

    public PetRegoException(final MessageCode messageCode, final String message) {
        super(message);
        this.messageCode = messageCode;
        this.message = message;
    }

    public final MessageCode getMessageCode() {
        return messageCode;
    }

    public final void setMessageCode(final MessageCode messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public final String getMessage() {
        return message;
    }

    public final void setMessage(final String message) {
        this.message = message;
    }
}
