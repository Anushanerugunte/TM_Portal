package bdaytemplate.customeexception;
import org.springframework.http.HttpStatus;

public class Exception {

    private final String message;
    private final HttpStatus httpStatus;

    public Exception(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
