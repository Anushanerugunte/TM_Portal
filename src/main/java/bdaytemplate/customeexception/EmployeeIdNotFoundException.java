package bdaytemplate.customeexception;

public class EmployeeIdNotFoundException extends RuntimeException{

    public EmployeeIdNotFoundException(String message) {
        super(message);
    }

    public EmployeeIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
