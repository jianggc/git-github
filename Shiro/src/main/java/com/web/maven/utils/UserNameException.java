package com.web.maven.utils;

import org.apache.shiro.authc.AccountException;

public class UserNameException extends AccountException {
	private static final long serialVersionUID = 1L;

	public UserNameException() {
        super();
    }

    public UserNameException(String message) {
        super(message);
    }

    public UserNameException(Throwable cause) {
        super(cause);
    }

    public UserNameException(String message, Throwable cause) {
        super(message, cause);
    }

}
