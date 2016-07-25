package vn.edu.fu.veazy.core.exception;

import vn.edu.fu.veazy.core.response.ResponseCode;

public class UsernameExpectedException extends CorruptedFormException {
    private final int ERROR_CODE = ResponseCode.USERNAME_EXPECTED;
    
    public UsernameExpectedException() {
        super();
    }
    
    public UsernameExpectedException(String msg) {
        super(msg);
    }
    
    public int getCode() {
        return this.ERROR_CODE;
    }
    
}
