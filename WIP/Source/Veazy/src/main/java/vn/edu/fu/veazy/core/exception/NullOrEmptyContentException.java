package vn.edu.fu.veazy.core.exception;

import vn.edu.fu.veazy.core.response.ResponseCode;

public class NullOrEmptyContentException extends CorruptedFormException {
    private final int ERROR_CODE = ResponseCode.NULL_EMPTY_CONTENT;

    public NullOrEmptyContentException() {
        super();
    }

    public NullOrEmptyContentException(String msg) {
        super(msg);
    }

    public int getCode() {
        return this.ERROR_CODE;
    }
    
}
