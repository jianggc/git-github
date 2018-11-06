package com.web.maven.utils;

import org.apache.shiro.authc.AccountException;

public class PasswordException extends AccountException {
    public PasswordException() {
        super();
    }

    public PasswordException(String message) {
        super(message);
    }

    public PasswordException(Throwable cause) {
        super(cause);
    }

    public PasswordException(String message, Throwable cause) {
        super(message, cause);
    }

}
