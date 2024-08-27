package org.revature.ers.exception.reimbursement;

import org.revature.ers.exception.CustomException;

public class InvalidReimbursementAmountException extends CustomException {
    public InvalidReimbursementAmountException(String message) {
        super(message);
    }

    public InvalidReimbursementAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
