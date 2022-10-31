package com.example.controller;


import com.example.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class AdminController
{
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    PoliciesRepository policiesRepository;

    @Autowired
    SellsRepository sellsRepository;

    @Autowired
    UserRepository userRepository;
}
