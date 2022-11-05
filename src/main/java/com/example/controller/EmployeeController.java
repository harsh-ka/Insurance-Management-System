package com.example.controller;

import com.example.dao.*;
import com.example.models.*;
import com.example.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.dao.EmployeeRepository;
import com.example.models.Employee;

import java.util.List;
import java.util.UUID;

@Controller
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
    InsuranceRepository insuranceRepository;
    @Autowired
    PoliciesRepository policiesRepository;

    @Autowired
    UserPhoneNumberRepository userPhoneNumberRepository;

    @Autowired
    SellsRepository sellsRepository;


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
        User user=userRepository.getUser(employee.getUsername());
        model.addAttribute("employee",employee);
        model.addAttribute("Phone",phone);
        model.addAttribute("user",user);
        return "employee/viewEmployee";
    }
    @GetMapping("employee/client")
    private String getClients(Model model)
    {
        User user=securityService.findLoggedInUser();
        Employee employee=employeeRepository.getByUsername(user.getUsername());
        List<Client> client = clientRepository.getbyEmployeeId(employee.getEmployeeId());
        model.addAttribute("Clients", client);

        return "employee/client";
    }

    @GetMapping("employee/client/{id}")
    private String getClient(@PathVariable("id") int clientNo, Model model)
    {
        Client client = clientRepository.getbyClientNo(clientNo);
        List<UserPhoneNumber> phone=userPhoneNumberRepository.getPhoneNumbersByUsername(client.getUsername());

        List<Sells> total_sell=sellsRepository.getsellbyclientNo(client.getClientNo());

        if(client == null)
        {
            return "redirect:/employee/client";
        }

        model.addAttribute("client", client);
        model.addAttribute("UserPhone",phone);
        model.addAttribute("Sells",total_sell);

        return "employee/viewClient";
    }
    @GetMapping("employee/client/{clientId}/edit")
    private String editClient(@PathVariable("clientId") int clientId,Model model,String success,String failed){
        Client client=clientRepository.getbyClientNo(clientId);
        User user=userRepository.getUser(client.getUsername());
        if(success!=null) model.addAttribute("success","Your update is success");
        if(failed!=null) model.addAttribute("error","Your update is failed");

        model.addAttribute("user",user);

        model.addAttribute("client",client);

        model.addAttribute("submiturl","/employee/client/"+clientId+"/edit");

        return "employee/editClient";

    }
    @PostMapping("employee/client/{clientId}/edit")
    private  String editClient(@PathVariable("clientId") int clientId,@ModelAttribute("user") User user,@RequestParam("middleName") String middleName,
                              @RequestParam("contact") String contact , Model model,BindingResult bindingResult){

        Client client=clientRepository.getbyClientNo(clientId);
        User curuser=userRepository.getUser(client.getUsername());
        client.setFirstName(user.getFirstName());
        client.setLastName(user.getLastName());
        client.setMiddleName(middleName);

        client.setClientEmail(user.getEmailAddress());
        client.setClientContact(contact);
        client.setLandMark(user.getAddress());

        curuser.setFirstName(user.getFirstName());
        curuser.setLastName(user.getLastName());
        curuser.setAddress(user.getAddress());
        curuser.setDateOfBirth(user.getDateOfBirth());
        curuser.setGender(user.getGender());

        String token= UUID.randomUUID().toString();

        curuser.setToken(token);

        int client_affect=clientRepository.updateClient(client);
        int user_affect=userRepository.updateUser(curuser);

        System.out.println("agent affected "+clientId+"user affected "+user_affect);
        String submit="employee/client/"+clientId+"/edit";
        if(client_affect!=0 && user_affect!=0){
            submit=submit+"?success";
        }
        else{
            model.addAttribute("user",curuser);
            submit=submit+"?failed";
        }
        model.addAttribute("submiturl","/employee/client/"+clientId+"/edit");
        return "redirect:/"+submit;
    }

    @GetMapping("employee/agents")
    private String getAgents(Model model)
    {
        User user=securityService.findLoggedInUser();
        Employee employee=employeeRepository.getByUsername(user.getUsername());
        List<Agent> agent = agentRepository.getAgentbyEmployeeId(employee.getEmployeeId());
        model.addAttribute("Agent", agent);

        return "employee/agents";
    }

    @GetMapping("employee/agents/{agentId}")
    private String getAgent(@PathVariable("agentId") int agentId, Model model)
    {
        Agent agent = agentRepository.getAgentByID(agentId);
        List<Sells> sells=sellsRepository.getsellbyagentId(agent.getAgentId());
        if(agent == null)
        {
            return "redirect:/employee/agents";
        }

        model.addAttribute("agent", agent);
        model.addAttribute("sells",sells);


        return "employee/viewAgent";
    }

    @GetMapping("employee/agents/{agentId}/edit")

    private String editAgent(@PathVariable("agentId") int agentId,Model model,String success,String failed){
        Agent agent = agentRepository.getAgentByID(agentId);
        User user=userRepository.getUser(agent.getUsername());
        if(success!=null) model.addAttribute("success","Your update is success");
        if(failed!=null) model.addAttribute("error","Your update is failed");

        model.addAttribute("user",user);

        model.addAttribute("agent",agent);

        model.addAttribute("submiturl","/employee/agents/"+agentId+"/edit");

        return "employee/editAgent";

    }

    @PostMapping("employee/agents/{agentId}/edit")
    private  String editAgent(@PathVariable("agentId") int agentId,@ModelAttribute("user") User user,@RequestParam("middleName") String middleName,
                              @RequestParam("commision") int commision,
                              Model model,BindingResult bindingResult){
        System.out.println("this is inside post mapping");
        System.out.println("your middle name is "+middleName);
        Agent agent=agentRepository.getAgentByID(agentId);
        User curuser=userRepository.getUser(agent.getUsername());
        agent.setFirstName(user.getFirstName());
        agent.setLastName(user.getLastName());
        agent.setMiddleName(middleName);
        agent.setLandmark(user.getAddress());
        agent.setCommision(commision);

        curuser.setFirstName(user.getFirstName());
        curuser.setLastName(user.getLastName());
        curuser.setAddress(user.getAddress());
        curuser.setDateOfBirth(user.getDateOfBirth());
        curuser.setGender(user.getGender());

        String token= UUID.randomUUID().toString();

        curuser.setToken(token);

        int agent_affect=agentRepository.updateAgent(agent);

        int user_affect=userRepository.updateUser(curuser);

        System.out.println("agent affected "+agent_affect+"user affected "+user_affect);
        String submit="employee/agents/"+agentId+"/edit";
        if(agent_affect!=0 && user_affect!=0){
            submit=submit+"?success";
        }
        else{
            model.addAttribute("user",curuser);
            submit=submit+"?failed";
        }
        model.addAttribute("submiturl","/employee/agents/"+agentId+"/edit");
        return "redirect:/"+submit;
    }

    @GetMapping("employee/policies")
    private String getPolicies(Model model)
    {
        List<Insurance> insurances=insuranceRepository.getAll();
        System.out.println(insurances);
        model.addAttribute("Insurance", insurances);

        return "employee/policies";
    }

    @GetMapping("employee/policies/{insuranceid}")
    private String getPolicy(@PathVariable("insuranceid") String insuranceid, Model model)
    {
        Insurance insurance=insuranceRepository.getInsurancebyId(insuranceid);
        List<Policies> policies = policiesRepository.getPoliciesByinsuranceId(insuranceid);
        if(policies == null)
        {
            return "redirect:/employee/policies";
        }
        model.addAttribute("insurance",insurance);
        model.addAttribute("Policies", policies);

        return "employee/viewPolicies";

    }

}
