package vn.edu.fu.veazy.core.exception;

import vn.edu.fu.veazy.core.response.ResponseCode;

public class EmailExpectedException extends CorruptedFormException {
    private final int ERROR_CODE = ResponseCode.EMAIL_EXPECTED;

    public EmailExpectedException() {
        super();
    }

    public EmailExpectedException(String msg) {
        super(msg);
    }

    public int getCode() {
        return this.ERROR_CODE;
    }
    
}
