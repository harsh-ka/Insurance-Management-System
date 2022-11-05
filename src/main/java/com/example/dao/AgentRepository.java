package com.example.dao;

import com.example.models.Agent;
import com.example.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AgentRepository {

    @Autowired
    private JdbcTemplate template;

    public void createAgent(Agent agent){
        String sql="INSERT INTO AGENT(agentID,licenceNo,firstName,middleName,lastName,houseNo,landmark,city,employeeId,admin_Id,Comission,username) Values(?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql, agent.getAgentId(),
                agent.getLicenceNo(),
                agent.getFirstName(),
                agent.getMiddleName(),
                agent.getLastName(),
                agent.getHouseNo(),
                agent.getLandmark(),
                agent.getCity(),
                agent.getEmployeeId(),
                agent.getAdmin_id(),
                agent.getCommision(),
                agent.getUsername());
    }

    public Agent getByUsername(String username)
    {
        String sql = "SELECT * FROM Agent WHERE username = ?";
        try
        {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Agent.class), new Object[]{username});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }


    public Agent getAgentByID(int agentId){
        String sql="Select * from Agent WHERE agentId=?";
        try{
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(Agent.class),new Object[]{agentId});
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public Agent getAgentBylicenseNo(String licenseNo){
        String sql="Select * from Agent WHERE licenceNo=?";
        try{
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(Agent.class),new Object[]{licenseNo});
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Agent> getAll(){
        String sql="SELECT * FROM Agent";
        return template.query(sql,(rs, rowNum) -> {
            Agent a=new BeanPropertyRowMapper<Agent>(Agent.class).mapRow(rs,rowNum);
            return a;
        });
    }

    public List<Agent> getAgentbyFirstName(String FirstName){
        String sql="Select * from Agent where firstName=?";
        try {
            return template.query(sql,(rs, rowNum) -> {
                Agent e=new BeanPropertyRowMapper<Agent>(Agent.class).mapRow(rs,rowNum);
                return e;
            }, new Object[] { FirstName });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public List<Agent> getAgentbyEmployeeId(String employeeId){
        String sql="Select * from Agent WHERE employeeId=?";
        try {
            return template.query(sql,(rs, rowNum) -> {
                Agent e=new BeanPropertyRowMapper<Agent>(Agent.class).mapRow(rs,rowNum);
                return e;
            }, new Object[] { employeeId });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
    public void updateAgent(Agent agent){
        String sql="Update Agent SET admin_id=?,employeeId=?,Commision=? Where agentId=?";

        return template.update(sql,agent.getFirstName(),agent.getLastName(),
                agent.getMiddleName(),agent.getLandmark(),
                agent.getCommision(),agent.getAgentId());
    }
}
