package bdaytemplate.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Entity
@Table(name = "details")
public class EmployeeDetails implements List<EmployeeDetails> {

    @Id
    private String employeeid;
    private String name;
    private LocalDate birthdate;
    private String email;
    private LocalDate joiningDate;
    private String reporting_manager;

    public EmployeeDetails() {
    }

    public EmployeeDetails(String employeeid, String name, LocalDate joiningdate, LocalDate birthdate, String email, String reporting_manager) {
        this.employeeid = employeeid;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.joiningDate = joiningdate;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
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
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", joiningdate=" + joiningDate +
                ", reporting_manager='" + reporting_manager + '\'' +
                '}';
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<EmployeeDetails> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(EmployeeDetails employeeDetails) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends EmployeeDetails> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends EmployeeDetails> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public EmployeeDetails get(int i) {
        return null;
    }

    @Override
    public EmployeeDetails set(int i, EmployeeDetails employeeDetails) {
        return null;
    }

    @Override
    public void add(int i, EmployeeDetails employeeDetails) {

    }

    @Override
    public EmployeeDetails remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<EmployeeDetails> listIterator() {
        return null;
    }

    @Override
    public ListIterator<EmployeeDetails> listIterator(int i) {
        return null;
    }

    @Override
    public List<EmployeeDetails> subList(int i, int i1) {
        return null;
    }



}
