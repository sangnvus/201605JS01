package vn.edu.fu.veazy.core.exception;

import vn.edu.fu.veazy.core.response.ResponseCode;

public class InvalidEmailException extends CorruptedFormException {
    private final int ERROR_CODE = ResponseCode.INVALID_EMAIL;

    public InvalidEmailException() {
        super();
    }

    public InvalidEmailException(String msg) {
        super(msg);
    }

    public int getCode() {
        return this.ERROR_CODE;
    }

}
