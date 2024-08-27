package org.revature.ers.exception.user;

import org.revature.ers.exception.CustomException;

public class InvalidUsernamePasswordException extends CustomException {
    public InvalidUsernamePasswordException(String message) {
        super(message);
    }

    public InvalidUsernamePasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
