package org.revature.ers.exception.user;

import org.revature.ers.exception.CustomException;

public class UsernameAlreadyExistException extends CustomException {

    public UsernameAlreadyExistException(String message) {
        super(message);
    }

    public UsernameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
