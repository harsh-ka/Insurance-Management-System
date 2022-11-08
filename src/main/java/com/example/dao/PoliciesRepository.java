package com.example.dao;

import com.example.models.Employee;
import com.example.models.Insurance;
import com.example.models.Policies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoliciesRepository {

    @Autowired
    private JdbcTemplate template;


    public void createpolicies(Policies policies)
    {
        String query = "INSERT INTO Policies(PolicyTerm, TotalAmount, StartDate, EndDate, InsuranceId) VALUES (?, ?, ?, ?, ?)";
        template.update(query, policies.getPolicyTerm(),
                policies.getTotalAmount(),
                policies.getStartDate(),
                policies.getEndDate(),
                policies.getInsuranceId());
    }

    public List<Policies> getAll()
    {
        String sql = "SELECT * FROM Policies";
        return template.query(sql,(rs, rowNum) -> {
            Policies p=new BeanPropertyRowMapper<Policies>(Policies.class).mapRow(rs,rowNum);
            return p;
        });
    }

    public List<Policies> getPoliciesByinsuranceId(String InsuranceId)
    {
        String sql = "SELECT * FROM Policies WHERE InsuranceId=?";
        try {
            return template.query(sql,(rs, rowNum) -> {
                Policies s =new BeanPropertyRowMapper<Policies>(Policies.class).mapRow(rs,rowNum);
                return s;
            },new Object[]{InsuranceId});
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public Policies getPoliciesByinsuranceIdandTerm(String insuranceId,int PolicyTerm)
    {
        String sql = "SELECT * FROM Policies WHERE insuranceId=? and policyTerm=?";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<Policies>(Policies.class),new Object[]{insuranceId,PolicyTerm});
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void updatepoliciesbyInsuranceIdandTerm(Policies policies)
    {
        String sql="UPDATE Policies TotalAmount = ?,EndDate = ? WHERE InsuranceId = ? AND PolicyTerm=?";

        template.update(sql,policies.getTotalAmount(),policies.getEndDate(),policies.getEndDate(),policies.getInsuranceId(),policies.getPolicyTerm());

    }
    public void deletePolicy(Policies policies)
    {
        String sql = "DELETE FROM Policies WHERE InsuranceId = ? AND PolicyTerm = ?";

        template.update(sql, policies.getInsuranceId(),policies.getPolicyTerm());
    }

}
