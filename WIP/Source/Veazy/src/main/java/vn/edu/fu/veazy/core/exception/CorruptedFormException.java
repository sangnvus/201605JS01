package vn.edu.fu.veazy.core.exception;

import vn.edu.fu.veazy.core.response.ResponseCode;

public class CorruptedFormException extends IllegalArgumentException {
    private final int ERROR_CODE = ResponseCode.CORRUPTED_FORM;
    
    public CorruptedFormException() {
        
    }
    
    public CorruptedFormException(String msg) {
        super(msg);
    }

    public int getCode() {
        return this.ERROR_CODE;
    }
    
}
