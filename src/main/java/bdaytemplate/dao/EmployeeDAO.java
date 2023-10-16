package bdaytemplate.dao;

import bdaytemplate.dto.EmailRequest;
import bdaytemplate.dto.EmployeeDetails;

import java.util.List;

public interface EmployeeDAO {
public  List<EmployeeDetails> findAllWithBirthday();

}
