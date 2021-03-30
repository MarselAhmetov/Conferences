package team404.conference.general.exception;

public enum StatusCode {
    SC_200(200, "OK"),
    SC_400(400, "Bad Request"),
    SC_401(401, "Unauthorized"),
    SC_403(403, "Forbidden"),
    SC_404(404, "Not found"),
    SC_415(415, "Unsupported Media Type"),
    SC_423(423, "Locked"),
    SC_429(429, "Too Many Requests");

    private final int status;
    private final String errorCode;


    StatusCode(int status, String errorCode) {
        this.status = status;
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
