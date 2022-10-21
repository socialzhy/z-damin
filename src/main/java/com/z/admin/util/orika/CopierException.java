package com.z.admin.util.orika;

/**
 * CopierException
 *
 * @author trang
 */
public class CopierException extends RuntimeException {

    public CopierException() {
        super();
    }

    public CopierException(String message) {
        super(message);
    }

    public CopierException(String message, Throwable cause) {
        super(message, cause);
    }

    public CopierException(Throwable cause) {
        super(cause);
    }

    public CopierException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}