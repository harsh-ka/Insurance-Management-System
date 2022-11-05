package com.example.dao;


import com.example.models.Sells;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellsRepository {
    @Autowired
    private JdbcTemplate template;

    public void SellsCreation(Sells sell)
    {
        String query = "INSERT INTO Sells(agentId, clientNo, policyTerm, insuranceId, buyDate, amount) VALUES(?,?,?,?,?,?)";
        template.update(query, sell.getAgentId(),
                sell.getClientNo(),
                sell.getPolicyTerm(),
                sell.getInsuranceId(),
                sell.getBuyDate(),
                sell.getAmount());
    }

    public List<Sells> getsellbyagentId(int agentId){
        String sql="SELECT * FROM Sells WHERE agentId=?";
        try
        {
            return template.query(sql,(rs, rowNum) ->{
               Sells s = new BeanPropertyRowMapper<Sells>(Sells.class).mapRow(rs,rowNum);
                return  s;
            },new Object[]{agentId});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public List<Sells> getsellbyclientNoAndAgentId(int clientNo,int agentId){
        String sql="SELECT * FROM Sells WHERE clientNo=? AND agentId=?";
        try
        {
            return template.query(sql,(rs, rowNum) ->{
                Sells s = new BeanPropertyRowMapper<Sells>(Sells.class).mapRow(rs,rowNum);
                return  s;
            },new Object[]{clientNo,agentId});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }
    public List<Sells> getsellbyclientNo(int clientNo)
    {
        String sql="SELECT * FROM Sells WHERE clientNo=?";
        try
        {
            return template.query(sql,(rs, rowNum) ->{
                Sells s = new BeanPropertyRowMapper<Sells>(Sells.class).mapRow(rs,rowNum);
                return  s;
            },new Object[]{clientNo});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }


    public List<Sells> getsellbyInsuranceid(String InsuranceId)
    {
        String sql = "SELECT * FROM Sells WHERE insuranceId = ?";
        try
        {
            return template.query(sql,(rs, rowNum) ->{
                Sells s = new BeanPropertyRowMapper<Sells>(Sells.class).mapRow(rs,rowNum);
                return  s;
            },new Object[]{InsuranceId});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public Sells getsellbyId(int agentId,int clientNo,String InsuranceId)
    {
        String sql = "SELECT * FROM Sells WHERE agentId = ? AND clientNo = ? AND insuranceId = ?";
        try
        {
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(Sells.class),new Object[]{agentId, clientNo, InsuranceId});
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public List<Sells> getAll()
    {
        String sql = "SELECT * FROM Sells";
        try
        {
            return template.query(sql,(rs, rowNum) ->{
                Sells s = new BeanPropertyRowMapper<Sells>(Sells.class).mapRow(rs,rowNum);
                return  s;
            });
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    public void updateSells(Sells sell)
    {
        String sql = "UPDATE Sells SET buyDate = ?, amount = ? WHERE agentId = ? AND clientNo = ? AND insuranceId = ?";
        template.update(sql,sell.getBuyDate(),sell.getAmount(), sell.getAgentId(), sell.getClientNo(), sell.getInsuranceId());
        return ;
    }


}
