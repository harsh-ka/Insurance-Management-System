package com.example.dao;

import com.example.models.Admin;
import com.example.models.Agent;
import com.example.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {
    @Autowired
    private JdbcTemplate template;


    public void createAdmin(Admin admin)
    {
        String query = "INSERT INTO Admin(admin_id, admin_name, admin_email, contactNo, username) VALUES (?, ?, ?, ?, ?)";
        template.update(query, admin.getAdmin_id(), admin.getAdmin_name(), admin.getAdmin_email(), admin.getContactNo(), admin.getUsername());
    }

    public Admin getByUsername(String username)
    {
        String sql = "SELECT * FROM Admin WHERE username = ?";
        try
        {
            return template.queryForObject(sql, new BeanPropertyRowMapper<Admin>(Admin.class), new Object[]{username});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }


    public Agent getAdminById(int admin_id){
        String query = "SELECT * FROM Admin WHERE admin_id=?";
        try
        {
            return template.queryForObject(query,new BeanPropertyRowMapper<Agent>(Agent.class),new Object[]{admin_id});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public void updateAdmin(Admin admin){
        String query = "UPDATE Admin SET admin_name = ?, admin_email = ? , contactNo = ? WHERE admin_id = ?";

        template.update(query,admin.getAdmin_name(),admin.getAdmin_email(),admin.getContactNo(),admin.getAdmin_id());

        return;
    }

    public List<Admin> getadminByname(String admin_name)
    {
        String query = "SELECT * FROM Admin WHERE admin_name = ?";
        try
        {
            return template.query(query,(rs, rowNum) -> {
                Admin e=new BeanPropertyRowMapper<Admin>(Admin.class).mapRow(rs,rowNum);
                return e;
            }, new Object[] { admin_name });
        } catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }
}
