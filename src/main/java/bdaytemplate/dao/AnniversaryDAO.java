package bdaytemplate.dao;

import bdaytemplate.dto.EmployeeDetails;

import java.util.List;

public interface AnniversaryDAO {

    public List<EmployeeDetails>findAllWithAnniversary();

    public List<EmployeeDetails> findById(String employeeid);

    public int save(EmployeeDetails anniversaryRequest);

    List<EmployeeDetails> allEmployeeDetails();

    public boolean employeeIdExists(String employeeId) ;

        public int deleteById(String employeeId);

    public int update(EmployeeDetails e);


}
