package org.revature.ers.exception.user;

import org.revature.ers.exception.CustomException;

public class UserIdNotFoundException extends CustomException {

    public UserIdNotFoundException(String message) {
        super(message);
    }

    public UserIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
