package com.mimogoods.dev.tools.basic.exception;
/**
 * @author William, Mao
 */
public class TestDelegateException extends RuntimeException{
    private final String messageCode;
    private final Throwable cause;
    private final String rootMessage;

    public TestDelegateException(Throwable cause, String rootMessage, String messageCode) {
        super(rootMessage, cause);
        this.messageCode = messageCode;
        this.cause = cause;
        this.rootMessage = rootMessage;
    }

    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public String getRootMessage() {
        return rootMessage;
    }
}
