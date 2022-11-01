package com.example.controller;

import com.example.dao.*;
import com.example.models.*;
import com.example.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dao.EmployeeRepository;
import com.example.models.Employee;
import java.util.List;

@Controller
@Transactional
public class EmployeeController
{
    @Autowired
    SecurityService securityService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    PoliciesRepository policiesRepository;

    @Autowired
    UserPhoneNumberRepository userPhoneNumberRepository;


    @GetMapping("/employee/dashboard")
    public String getDashboard()
    {
        return "employee/dashboard";
    }

    @GetMapping("employee/profile")
    public String getProfile(Model model)
    {
        User user = securityService.findLoggedInUser();
        List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberRepository.getPhoneNumbersByUsername(user.getUsername());
        Employee employee = employeeRepository.getByUsername(user.getUsername());
        System.out.println(employee);
        if(employee==null)
            return "redirect:/employee/dashboard";

        System.out.println(employee);

        model.addAttribute("user", user);
        model.addAttribute("employee", employee);
        model.addAttribute("userPhoneNumber",userPhoneNumbers);
        return "employee/profile";
    }

    @GetMapping("employee/employee")
    private String getEmployees(Model model)
    {
        List<Employee> employee = employeeRepository.getAll();
        System.out.println(employee);
        model.addAttribute("Employee", employee);

        return "employee/employee";
    }

    @GetMapping("employee/employee/{id}")

    private String getEmployee(@PathVariable("id") String employeeId,Model model){
        Employee employee=employeeRepository.getEmployeebyId(employeeId);

        List<UserPhoneNumber> phone=userPhoneNumberRepository.getPhoneNumbersByUsername(employee.getUsername());

        model.addAttribute("Employee",employee);
        model.addAttribute("Phone",phone);
        return "employee/viewEmployee";
    }
    @GetMapping("employee/client")
    private String getClients(Model model)
    {
        User user=securityService.findLoggedInUser();
        Employee employee=employeeRepository.getByUsername(user.getUsername());
        List<Client> client = clientRepository.getbyEmployeeId(employee.getEmployeeId());
        model.addAttribute("Client", client);

        return "employee/client";
    }

    @GetMapping("employee/client/{id}")
    private String getClient(@PathVariable("id") int clientNo, Model model)
    {
        Client client = clientRepository.getbyClientNo(clientNo);
        if(client == null)
        {
            return "redirect:/employee/client";
        }

        model.addAttribute("Client", client);

        return "employee/viewClient";
    }

    @GetMapping("employee/agent")
    private String getAgents(Model model)
    {
        User user=securityService.findLoggedInUser();
        Employee employee=employeeRepository.getByUsername(user.getUsername());
        List<Agent> agent = agentRepository.getAgentbyEmployeeId(employee.getEmployeeId());
        model.addAttribute("Agent", agent);

        return "employee/agent";
    }

    @GetMapping("employee/agent/{agentId}")
    private String getAgent(@PathVariable("agentId") int agentId, Model model)
    {
        Agent agent = agentRepository.getAgentByID(agentId);
        if(agent == null)
        {
            return "redirect:/employee/agent";
        }

        model.addAttribute("Agent", agent);

        return "employee/viewAgent";
    }

    @GetMapping("employee/policies")
    private String getPolicies(Model model)
    {
        List<Policies> policies= policiesRepository.getAll();
        model.addAttribute("Policies", policies);

        return "employee/policies";
    }

    @GetMapping("employee/policies/{insuranceid}")
    private String getPolicy(@PathVariable("insuranceid") String insuranceid, Model model)
    {
        List<Policies> policies = policiesRepository.getPoliciesByinsuranceId(insuranceid);
        if(policies == null)
        {
            return "redirect:/employee/policies";
        }

        model.addAttribute("Policies", policies);

        return "employee/viewPolicies";

    }

}
