package bdaytemplate.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "details")
public class EmailRequest {

    @Id
    private String employeeid; // Change the data type to String

    private String name;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthdate; // Change the data type to java.util.Date

    private String email;
    private String reporting_manager;

    public EmailRequest() {
    }

    public EmailRequest(String employeeid, String name, Date Birthdate, String email, String reporting_manager) {
        this.employeeid = employeeid;
        this.name = name;
        this.birthdate = Birthdate;
        this.email = email;
        this.reporting_manager = reporting_manager;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReporting_manager() {
        return reporting_manager;
    }

    public void setReporting_manager(String reporting_manager) {
        this.reporting_manager = reporting_manager;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "employeeid='" + employeeid + '\'' +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                ", reporting_manager='" + reporting_manager + '\'' +
                '}';
    }
}
