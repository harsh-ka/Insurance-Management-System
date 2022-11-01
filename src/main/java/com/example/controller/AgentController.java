package com.example.controller;

import com.example.dao.AgentRepository;
import com.example.dao.EmployeeRepository;
import com.example.dao.UserPhoneNumberRepository;
import com.example.models.Agent;
import com.example.models.Employee;
import com.example.models.User;
import com.example.models.UserPhoneNumber;
import com.example.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Transactional
public class AgentController
{
    @Autowired
    SecurityService securityService;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserPhoneNumberRepository userPhoneNumberRepository;

    @GetMapping("/agent/dashboard")
    public String getDashboard()
    {
        return "agent/dashboard";
    }

    @GetMapping("/agent/agent")
    public String getAgents(Model model)
    {
        List<Agent> agent = agentRepository.getAll();

        model.addAttribute("Agent", agent);

        return "agent/agent";
    }

    @GetMapping("agent/profile")
    public String getProfile(Model model)
    {
        User user = securityService.findLoggedInUser();

        Agent agent = agentRepository.getByUsername(user.getUsername());
        if(agent==null){
            return "redirect:/agent/dashboard";
        }

        model.addAttribute("User", user);

        model.addAttribute("Agent", agent);

        return "agent/profile";
    }

    @GetMapping("agent/employee/{id}")
    public String getEmployee(@PathVariable("id") String employeeId,Model model){

        Employee employee=employeeRepository.getEmployeebyId(employeeId);

        List<UserPhoneNumber> phone=userPhoneNumberRepository.getPhoneNumbersByUsername(employee.getUsername());

        model.addAttribute("Employee",employee);

        model.addAttribute("Phone",phone);

        return "employee/viewEmployee";
    }



}
