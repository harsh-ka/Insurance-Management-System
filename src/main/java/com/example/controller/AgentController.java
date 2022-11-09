package com.example.controller;

import com.example.dao.*;
import com.example.models.*;
import com.example.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AgentController
{
    @Autowired
    SecurityService securityService;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserPhoneNumberRepository userPhoneNumberRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellsRepository sellsRepository;

    @Autowired
    PoliciesRepository policiesRepository;
    @Autowired
    InsuranceRepository insuranceRepository;

    @GetMapping("/agent/dashboard")
    public String getDashboard()
    {
        return "agent/dashboard";
    }

    @GetMapping("agent/profile")
    public String getProfile(Model model)
    {
        User user = securityService.findLoggedInUser();
        if(user==null){
            return "redirect:/user/login";
        }
        Agent agent = agentRepository.getByUsername(user.getUsername());

        if(agent==null){
            return "redirect:/agent/dashboard";
        }

        model.addAttribute("user", user);

        model.addAttribute("agent", agent);

        return "agent/viewProfile";
    }

    @GetMapping("/agent/agents")
    public String getAgents(Model model)
    {
        List<Agent> agent = agentRepository.getAll();

        model.addAttribute("Agent", agent);

        return "agent/agents";
    }
    @GetMapping("agent/agents/{agentId}")
    public String viewAgent(@PathVariable("agentId") int agentId, Model model) {
        Agent agent = agentRepository.getAgentByID(agentId);

        if (agent == null) return "redirect:/agent/agents";
        //List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberRepository.getPhoneNumbersByUsername(agent.getUsername());
        List<Sells> sells = sellsRepository.getsellbyagentId(agentId);

        model.addAttribute("agent", agent);
       // model.addAttribute("numbers", userPhoneNumbers);
        model.addAttribute("sells", sells);

        return "agent/viewAgents";
    }

    @GetMapping("/agent/clients")
    public String getClients(Model model)
    {
        List<Client> clients = clientRepository.getAll();

        model.addAttribute("Clients", clients);

        return "agent/clients";
    }

    @GetMapping("agent/clients/{id}")
    public String viewClient(@PathVariable("id") int id, Model model) {

        Client client = clientRepository.getbyClientNo(id);

        if (client == null) return "redirect:/agent/clients";

        User user = userRepository.getUser(client.getUsername());

        List<Sells> sells=sellsRepository.getsellbyclientNo(client.getClientNo());

        model.addAttribute("client", client);

        model.addAttribute("user", user);

        model.addAttribute("Sells",sells);

        return "agent/viewClients";
    }



    @GetMapping("agent/employees/{id}")
    private String getEmployee(@PathVariable("id") String employeeId, Model model) {

        Employee employee = employeeRepository.getEmployeebyId(employeeId);

        if (employee == null) return "redirect:/agent/profile";
        List<UserPhoneNumber> phone = userPhoneNumberRepository.getPhoneNumbersByUsername(employee.getUsername());

        List<Agent> agents = agentRepository.getAgentbyEmployeeId(employeeId);
        List<Client> clients = clientRepository.getbyEmployeeId(employee.getEmployeeId());
        User user=userRepository.getUser(employee.getUsername());
        model.addAttribute("user",user);
        model.addAttribute("employee", employee);

        model.addAttribute("Phone", phone);

        model.addAttribute("agents", agents);
        model.addAttribute("Client", clients);

        return "agent/viewEmployee";
    }

    @GetMapping("agent/insurance/")

    private String getInsurances(Model model){
        List<Insurance> insurance=insuranceRepository.getAll();

        model.addAttribute("Insurance",insurance);

        return "agent/insurance";
    }
    @GetMapping("agent/insurance/{insuranceId}")
    private String getInsurance(@PathVariable("insuranceId") String insuranceId, Model model)
    {
        Insurance insurance = insuranceRepository.getInsurancebyId((insuranceId));
        if(insurance == null)
        {
            return "redirect:/agent/dashboard";
        }
        List<Policies> policies=policiesRepository.getPoliciesByinsuranceId(insurance.getInsuranceId());
        model.addAttribute("insurance", insurance);
        model.addAttribute("Policies",policies);

        return "agent/viewInsurance";

    }

    @GetMapping("agent/sells")

    public String getSells(Model model){
        User user=securityService.findLoggedInUser();
        if(user==null) return "redirect:/user/login";
        Agent agent=agentRepository.getByUsername(user.getUsername());
        List<Sells> sells=sellsRepository.getsellbyagentId(agent.getAgentId());
        model.addAttribute("Sells",sells);
        return "agent/sells";
    }

}
