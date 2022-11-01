package com.example.dao;

import com.example.models.Employee;
import com.example.models.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InsuranceRepository {
    @Autowired
    private JdbcTemplate template;
    public void createInsurance(Insurance insurance){
        String sql="INSERT INTO Insurance(InsuranceId,InsuranceType,InsuranceName) VALUES(?,?,?)";
        template.update(sql,insurance.getInsuranceId(),insurance.getInsuranceType(),insurance.getInsuranceName());
        return;
    }
    public Insurance getInsurancebyId(String InsuranceId){

        String sql="Select * from Insurance where InsuranceId=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(Insurance.class), new Object[] { InsuranceId });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void updateInsurancebyId(Insurance insurance){

        String sql="UPDATE Insurance SET InsuranceType=?,InsuranceName=? WHERE InsuranceId=?";

        template.update(sql,insurance.getInsuranceType(),insurance.getInsuranceName(),insurance.getInsuranceId());

        return;
    }

    public List<Insurance> getAll(){
        String sql="Select * from Insurance";
        try {
            return template.query(sql,(rs, rowNum) -> {
             Insurance insurance=new BeanPropertyRowMapper<Insurance>(Insurance.class).mapRow(rs,rowNum);
             return insurance;
            });
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
