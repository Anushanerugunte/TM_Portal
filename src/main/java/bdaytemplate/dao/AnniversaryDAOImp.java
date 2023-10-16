package bdaytemplate.dao;

import bdaytemplate.dto.EmployeeDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnniversaryDAOImp implements AnniversaryDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    @Override
    public List<EmployeeDetails> findAllWithAnniversary() {
        String SQL = "SELECT employeeid,name,birthdate,joiningdate,email,reporting_manager FROM details WHERE  MONTH(joiningdate) = MONTH(CURRENT_DATE()) AND DAY(joiningdate) = DAY(CURRENT_DATE())";
        return getNamedParameterJdbcTemplate().query(SQL, new BeanPropertyRowMapper<>(EmployeeDetails.class));
    }

    @Override
    public int save(EmployeeDetails e) {
        String sql = "INSERT INTO details (employeeid, name, joiningdate, birthdate, email, reporting_manager) VALUES (:employeeid, :name, :joiningdate, :birthdate, :email, :reporting_manager)";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("employeeid", e.getEmployeeid());
        paramSource.addValue("name", e.getName());
        paramSource.addValue("birthdate", e.getBirthdate());
        paramSource.addValue("joiningdate", e.getJoiningdate());
        paramSource.addValue("email", e.getEmail());
        paramSource.addValue("reporting_manager", e.getReporting_manager());

        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public boolean employeeIdExists(String employeeId) {
        String sql = "SELECT COUNT(*) FROM details WHERE employeeid = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, employeeId);
        return count > 0;
    }

    @Override
    public List<EmployeeDetails> allEmployeeDetails() {
        String SQL = "SELECT * FROM details";
        return getNamedParameterJdbcTemplate().query(SQL, new BeanPropertyRowMapper<>(EmployeeDetails.class));
    }

    @Override
    public int deleteById(String employeeId) {
        return jdbcTemplate.update("DELETE FROM details WHERE employeeid=?", employeeId);
    }

    @Override
    public int update(EmployeeDetails e) {
        String sql = "update details set name= :name, joiningdate= :joiningdate, birthdate= :birthdate, email=:email, reporting_manager=:reporting_manager where employeeid= :employeeid ";
        SqlParameterSource paramSource = new MapSqlParameterSource()
                .addValue("employeeid", e.getEmployeeid())
                .addValue("name", e.getName())
                .addValue("joiningdate", e.getJoiningdate())
                .addValue("birthdate", e.getBirthdate())
                .addValue("email", e.getEmail())
                .addValue("reporting_manager", e.getReporting_manager());

        return namedParameterJdbcTemplate.update(sql, paramSource);
    }

    @Override
    public List<EmployeeDetails> findById(String employeeid) {
        SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("employeeid", employeeid);
        return getNamedParameterJdbcTemplate().query("SELECT * FROM details WHERE employeeid = :employeeid ", mapSqlParameterSource, new BeanPropertyRowMapper<>(EmployeeDetails.class));
    }

}
