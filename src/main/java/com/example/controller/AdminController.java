package com.example.controller;


import com.example.dao.*;
import com.example.models.*;
import com.example.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;
import java.sql.Date;

@Controller

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

    @Autowired
    UserPhoneNumberRepository userPhoneNumberRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SecurityService securityService;

    @GetMapping("/admin/dashboard")
    public String getdashboard(Model model){
        System.out.println("inside this dashboard function");
        return "/admin/dashboard";
    }
    @GetMapping("admin/agents")
    public String getAgents(Model model){
        List<Agent> agents=agentRepository.getAll();

        if(agents.size()==0) return "redirect:/admin/dashboard";

        model.addAttribute("Agent",agents);

        return "admin/agents";
    }

    @GetMapping("admin/agents/{agentId}")
    public String viewAgent(@PathVariable("agentId") int agentId, Model model){
        Agent agent=agentRepository.getAgentByID(agentId);
        List<UserPhoneNumber> userPhoneNumbers=userPhoneNumberRepository.getPhoneNumbersByUsername(agent.getUsername());
        List<Sells> sells=sellsRepository.getsellbyagentId(agentId);

        if(agent==null) return "redirect:/admin/agents";

        model.addAttribute("agent",agent);
        model.addAttribute("numbers",userPhoneNumbers);
        model.addAttribute("sells",sells);

        return "admin/viewAgent";
    }


    @GetMapping("admin/employees")
    private String getEmployees(Model model)
    {
        List<Employee> employee = employeeRepository.getAll();

        if(employee.size()==0) return "redirect:/admin/dashboard";

        model.addAttribute("Employee", employee);

        return "admin/employees";
    }
    @GetMapping("admin/employees/{id}")
    private String getEmployee(@PathVariable("id") String employeeId,Model model){

        Employee employee=employeeRepository.getEmployeebyId(employeeId);
        List<UserPhoneNumber> phone=userPhoneNumberRepository.getPhoneNumbersByUsername(employee.getUsername());

        List<Agent> agents=agentRepository.getAgentbyEmployeeId(employeeId);
        List<Client> clients=clientRepository.getbyEmployeeId(employee.getEmployeeId());

        if(employee==null) return "redirect:/admin/employees";

        model.addAttribute("employee",employee);

        model.addAttribute("Phone",phone);

        model.addAttribute("agents",agents);
        model.addAttribute("Client",clients);

        return "admin/viewEmployee";
    }

    @GetMapping("admin/clients")
    public String getClient(Model model)
    {
        List<Client> client = clientRepository.getAll();
        model.addAttribute("Client", client);

        return "admin/clients";
    }

    @GetMapping("admin/clients/{id}")
    public String viewClient(@PathVariable("id") int id,Model model){

        Client client=clientRepository.getbyClientNo(id);

        User user=userRepository.getUser(client.getUsername());

        if(client==null) return "redirect:/admin/clients";

        model.addAttribute("client",client);

        model.addAttribute("user",user);

        return "admin/viewClient";
    }

    @GetMapping("admin/policies")
    private String getPolicies(Model model)
    {
        List<Insurance> insurances=insuranceRepository.getAll();
        System.out.println(insurances);
        model.addAttribute("Insurance", insurances);

        return "admin/policies";
    }

    @GetMapping("admin/policies/{insuranceid}")
    private String getPolicy(@PathVariable("insuranceid") String insuranceid, Model model)
    {
        Insurance insurance=insuranceRepository.getInsurancebyId(insuranceid);
        List<Policies> policies = policiesRepository.getPoliciesByinsuranceId(insuranceid);
        if(policies.size() == 0 | insurance==null)
        {
            return "redirect:/employee/policies";
        }
        model.addAttribute("insurance",insurance);
        model.addAttribute("Policies", policies);

        return "admin/viewPolicy";

    }
    @GetMapping("admin/sells")
    private String getsells(Model model)
    {
        List<Sells> sells = sellsRepository.getAll();

        if(sells.size() == 0)
        {
            return "redirect:/client/client";
        }
        model.addAttribute("Sells", sells);

        return "admin/sells";
    }


    @GetMapping("admin/employees/{id}/edit")
    private String editEmployees(@PathVariable("id") String employeeId, Model model,String success,String failed){

        if(success!=null) model.addAttribute("success","Your update is successful");

        if(failed!=null) model.addAttribute("error","Your update is unsuccessful");

        Employee employee=employeeRepository.getEmployeebyId(employeeId);

        User user=userRepository.getUser(employee.getUsername());

        model.addAttribute("user",user);
        model.addAttribute("employee",employee);
        model.addAttribute("submiturl","/admin/employees/"+employeeId+"/edit");

        return "admin/editEmployees";
    }

    @PostMapping("admin/employees/{id}/edit")
    private  String editEmployees(@PathVariable("id") String employeeId, @ModelAttribute("user") User user, @RequestParam("middleName") String middleName
                               ,Model model, BindingResult bindingResult){

        Employee employee=employeeRepository.getEmployeebyId(employeeId);

        User curuser=userRepository.getUser(employee.getUsername());
        employee.setFirstName(user.getFirstName());
        employee.setLastName(user.getLastName());
        employee.setMiddleName(middleName);
        employee.setEmail(user.getEmailAddress());
        employee.setEmployeeAddress(user.getAddress());

        curuser.setFirstName(user.getFirstName());
        curuser.setLastName(user.getLastName());
        curuser.setAddress(user.getAddress());
        curuser.setDateOfBirth(user.getDateOfBirth());
        curuser.setGender(user.getGender());

        String token= UUID.randomUUID().toString();

        curuser.setToken(token);

        int employee_affect=employeeRepository.updateEmployee(employee);
        int user_affect=userRepository.updateUser(curuser);

        System.out.println("agent affected "+employeeId+"user affected "+user_affect);
        String submit="admin/employees/"+employeeId+"/edit";
        if(employee_affect!=0 && user_affect!=0){
            submit=submit+"?success";
        }
        else{
            model.addAttribute("user",curuser);
            submit=submit+"?failed";
        }
        model.addAttribute("submiturl","admin/employees/"+employeeId+"/edit");
        return "redirect:/"+submit;

}

    @GetMapping("admin/agents/{agentId}/edit")
    private String editAgent(@PathVariable("agentId") int agentId,Model model,String success,String failed){
        Agent agent = agentRepository.getAgentByID(agentId);
        User user=userRepository.getUser(agent.getUsername());
        if(success!=null) model.addAttribute("success","Your update is success");
        if(failed!=null) model.addAttribute("error","Your update is failed");

        model.addAttribute("user",user);

        model.addAttribute("agent",agent);

        model.addAttribute("submiturl","/admin/agents/"+agentId+"/edit");
        return "admin/editAgents";

    }

    @PostMapping("admin/agents/{agentId}/edit")
    private  String editAgent(@PathVariable("agentId") int agentId,@ModelAttribute("user") User user,@RequestParam("middleName") String middleName,
                              @RequestParam("commision") int commision,
                              Model model,BindingResult bindingResult) {
        System.out.println("this is inside post mapping");
        System.out.println("your middle name is " + middleName);
        Agent agent = agentRepository.getAgentByID(agentId);
        User curuser = userRepository.getUser(agent.getUsername());
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

        String token = UUID.randomUUID().toString();

        curuser.setToken(token);

        int agent_affect = agentRepository.updateAgent(agent);

        int user_affect = userRepository.updateUser(curuser);

        System.out.println("agent affected " + agent_affect + "user affected " + user_affect);
        String submit = "admin/agents/" + agentId + "/edit";
        if (agent_affect != 0 && user_affect != 0) {
            submit = submit + "?success";
        } else {
            model.addAttribute("user", curuser);
            submit = submit + "?failed";
        }
        model.addAttribute("submiturl", "/admin/agents/" + agentId + "/edit");
        return "redirect:/" + submit;
    }


    @GetMapping("admin/clients/{clientId}/edit")
    private String editClient(@PathVariable("clientId") int clientId, Model model, String success, String failed) {
        Client client = clientRepository.getbyClientNo(clientId);
        User user = userRepository.getUser(client.getUsername());
        if (success != null) model.addAttribute("success", "Your update is success");
        if (failed != null) model.addAttribute("error", "Your update is failed");

        model.addAttribute("user", user);

        model.addAttribute("client", client);

        model.addAttribute("submiturl", "/admin/clients/" + clientId + "/edit");

        return "admin/editClients";

    }

    @PostMapping("admin/clients/{clientId}/edit")
    private String editClient(@PathVariable("clientId") int clientId, @ModelAttribute("user") User user, @RequestParam("middleName") String middleName,
                              @RequestParam("contact") String contact, Model model, BindingResult bindingResult) {

        Client client = clientRepository.getbyClientNo(clientId);
        User curuser = userRepository.getUser(client.getUsername());
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

        String token = UUID.randomUUID().toString();

        curuser.setToken(token);

        int client_affect = clientRepository.updateClient(client);
        int user_affect = userRepository.updateUser(curuser);

        System.out.println("agent affected " + clientId + "user affected " + user_affect);
        String submit = "admin/clients/" + clientId + "/edit";
        if (client_affect != 0 && user_affect != 0) {
            submit = submit + "?success";
        } else {
            model.addAttribute("user", curuser);
            submit = submit + "?failed";
        }
        model.addAttribute("submiturl", "/admin/clients/" + clientId + "/edit");
        return "redirect:/" + submit;
    }
