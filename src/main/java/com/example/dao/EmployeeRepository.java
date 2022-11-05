package com.example.dao;

import com.example.models.Client;
import com.example.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeRepository
{
    @Autowired
    private JdbcTemplate template;
    public void createEmployee(Employee employee)
    {
        System.out.println("We are in dao ");
        //String query = "CREATE TABLE IF NOT EXISTS Employee(INT employeeId, DATE joinDate, DATE endDate, STRING FirstName, STRING MiddleName, STRING LastName, STRING email, STRING employeeAddress)";
        String query = "INSERT INTO Employee(employeeId,joinDate,endDate,firstName,middleName,lastName,email,employeeAddress,admin_id,username) VALUES (?,?,?,?,?,?,?,?,?,?)";
        template.update(query, employee.getEmployeeId(),
                employee.getJoinDate(),
                employee.getEndDate(),
                employee.getFirstName(),
                employee.getMiddleName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getEmployeeAddress(),
                employee.getAdmin_id(),
                employee.getUsername());
    }

    public Employee getByUsername(String username)
    {
        String sql = "SELECT * FROM Employee WHERE username = ?";
        try
        {
            return template.queryForObject(sql, new BeanPropertyRowMapper<Employee>(Employee.class), new Object[]{username});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }


    public Employee getEmployeebyId(String employeeId)
    {
        String sql="Select * from Employee where employeeId=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<Employee>(Employee.class), new Object[] { employeeId });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Employee> getEmployeebyFirstName(String FirstName){
        String sql="Select * from Employee where firstName=?";
        try {
            return template.query(sql,(rs, rowNum) -> {
                Employee e=new BeanPropertyRowMapper<Employee>(Employee.class).mapRow(rs,rowNum);
                return e;
            }, new Object[]{FirstName});
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public int updateEmployee(Employee employee){
        String sql="UPDATE Employee SET firstName=?, middleName=?, lastName=?, email=?,employeeAddress=? WHERE employeeId = ?";

           return template.update(sql, employee.getFirstName(),
                            employee.getMiddleName(),
                            employee.getLastName(),
                               employee.getEmail(),
                               employee.getEmployeeAddress(),
                               employee.getEmployeeId());

    }
    public List<Employee>  getAll(){
        String sql="Select * from Employee";

        try {
            return template.query(sql, (rs, rowNum) -> {
                Employee e = new BeanPropertyRowMapper<Employee>(Employee.class).mapRow(rs, rowNum);
                return e;
            });
        } catch (EmptyResultDataAccessException e){
            return  null;
        }
    }
}
