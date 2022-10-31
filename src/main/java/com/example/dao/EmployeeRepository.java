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
        String query = "INSERT INTO Employee(employeeId,joinDate,endDate,FirstName,MiddleName,LastName,email,employeeAddress,admin_id,username) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), new Object[]{username});
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
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Employee.class), new Object[] { employeeId });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Employee> getEmployeebyFirstName(String FirstName){
        String sql="Select * from Employee where FirstName=?";
        try {
            return template.query(sql,(rs, rowNum) -> {
                Employee e=new BeanPropertyRowMapper<Employee>(Employee.class).mapRow(rs,rowNum);
                return e;
            }, new Object[]{FirstName});
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public void UpdateEmployee(Employee employee){
        String sql="UPDATE Employee SET  email=?,employeeAddress=? WHERE employeeId = ?";
        try {
            template.update(sql, employee.getEmail(), employee.getEmployeeAddress(), employee.getEmployeeId());
            return;
        }
        catch (EmptyResultDataAccessException e){
            return;
        }
    }
    public List<Employee>  getAll(){
        String sql="Select * from Employee";
        return template.query(sql,(rs, rowNum) -> {
            Employee e=new BeanPropertyRowMapper<Employee>(Employee.class).mapRow(rs,rowNum);
            return e;
        });
    }
}
