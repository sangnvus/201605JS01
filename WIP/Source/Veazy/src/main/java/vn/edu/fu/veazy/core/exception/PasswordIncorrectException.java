package vn.edu.fu.veazy.core.exception;

import vn.edu.fu.veazy.core.response.ResponseCode;

public class PasswordIncorrectException extends IllegalArgumentException {
    private final int ERROR_CODE = ResponseCode.PASSWORD_INCORRECT;
    
    public PasswordIncorrectException() {
        
    }
    
    public PasswordIncorrectException(String msg) {
        super(msg);
    }

    public int getCode() {
        return this.ERROR_CODE;
    }
    
}
