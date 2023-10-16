package bdaytemplate.dao;

import bdaytemplate.dto.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImp implements EmployeeDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    @Override
    public List<EmployeeDetails> findAllWithBirthday() {
        String SQL = "SELECT employeeid,name,birthdate,joiningdate,email,reporting_manager FROM details WHERE  MONTH(birthdate) = MONTH(CURRENT_DATE()) AND DAY(birthdate) = DAY(CURRENT_DATE())";
        return getNamedParameterJdbcTemplate().query(SQL, new BeanPropertyRowMapper<>(EmployeeDetails.class));
    }

}
