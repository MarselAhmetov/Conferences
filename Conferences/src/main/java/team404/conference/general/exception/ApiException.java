package team404.conference.general.exception;

public class ApiException extends RuntimeException {

    private StatusCode statusCode;
    private final String error;
    
    public ApiException(String msg, String error, StatusCode statusCode)   {
        super(msg);
        this.statusCode = statusCode;
        this.error = error;
    }

    public ApiException(String msg, String error, StatusCode statusCode, Exception e)  {
        super(msg, e);
        this.statusCode = statusCode;
        this.error = error;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
        
    public StatusCode getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }
}
