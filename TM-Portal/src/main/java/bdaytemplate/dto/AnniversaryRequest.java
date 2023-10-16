package bdaytemplate.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class AnniversaryRequest {
    @Id
    private String employeeid;

    private String name;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate joiningDate;

    private String email;
    private String reporting_manager;

    public AnniversaryRequest() {
    }

    public AnniversaryRequest(String employeeid, String name, LocalDate joiningdate, String email, String reporting_manager) {
        this.employeeid = employeeid;
        this.name = name;
        this.joiningDate = joiningdate;
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

    public LocalDate getJoiningdate() {
        return joiningDate;
    }

    public void setJoiningdate(LocalDate joiningdate) {
        this.joiningDate = joiningdate;
    }

    public String getReportingmanager() {
        return reporting_manager;
    }

    public void setReportingmanager(String reporting_manager) {
        this.reporting_manager = reporting_manager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AnniversaryRequest [employeeid=" + employeeid + ", name=" + name + ", joiningdate=" + joiningDate + ", email="
                + email + ", reporting_manager=" + reporting_manager + "]";
    }
}
