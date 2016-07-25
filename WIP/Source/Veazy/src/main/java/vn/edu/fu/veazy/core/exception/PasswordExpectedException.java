package vn.edu.fu.veazy.core.exception;

import vn.edu.fu.veazy.core.response.ResponseCode;

public class PasswordExpectedException extends CorruptedFormException {
    private final int ERROR_CODE = ResponseCode.PASSWORD_EXPECTED;
    
    public PasswordExpectedException() {
        super();
    }

    public PasswordExpectedException(String msg) {
        super(msg);
    }

    public int getCode() {
        return this.ERROR_CODE;
    }
    
}
