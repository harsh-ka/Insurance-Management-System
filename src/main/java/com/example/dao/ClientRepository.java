package com.example.dao;

import com.example.models.Agent;
import com.example.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {

    @Autowired
    private JdbcTemplate template;

    public void createClient(Client client)
    {
        String sql = "INSERT INTO Client(clientNo,FirstName,MiddleName,LastName,clientEmail,clientContact,HouseNo,landMark,city,employeeId,username) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,client.getClientNo(),
                client.getFirstName(),
                client.getMiddleName(),
                client.getLastName(),
                client.getClientEmail(),
                client.getClientContact(),
                client.getHouseNo(),
                client.getLandMark(),
                client.getCity(),
                client.getEmployeeId(),
                client.getUsername());
    }

    public List<Client> getAll(){
        String sql="SELECT * FROM Client";
        return template.query(sql,(rs, rowNum) -> {
            Client a=new BeanPropertyRowMapper<Client>(Client.class).mapRow(rs,rowNum);
            return a;
        });
    }

    public List<Client> getbyEmployeeId(String employeeId)
    {
        String sql = "SELECT * FROM Client WHERE employeeId = ?";
        try {
            return template.query(sql,(rs, rowNum) -> {
                Client a=new BeanPropertyRowMapper<Client>(Client.class).mapRow(rs,rowNum);
                return a;
            }, new Object[] { employeeId });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public Client getbyClientNo(int clientNo)
    {
        String sql = "SELECT * FROM Client WHERE clientNo = ?";
        try
        {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Client.class), new Object[]{clientNo});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }



    public Client getByUsername(String username)
    {
        String sql = "SELECT * FROM Client WHERE username = ?";
        try
        {
            return template.queryForObject(sql, new BeanPropertyRowMapper<Client>(Client.class), new Object[]{username});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

}
