package com.example.controller;

import com.example.dao.*;
import com.example.models.*;
import com.example.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.models.Client;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Path;
import java.sql.Date;
import java.util.List;

@Controller
public class ClientController
{
    @Autowired
    SecurityService securityService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SellsRepository sellsRepository;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    PoliciesRepository policiesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AgentRepository agentRepository;


    @GetMapping("/client/dashboard")
    public String getDashboard(Model model)
    {
        User user=securityService.findLoggedInUser();
        Client client=clientRepository.getByUsername(user.getUsername());

        model.addAttribute("client",client);
        return "/client/dashboard";
    }

    @GetMapping("/client/profile")

    public String getProfile(Model model){
        User user=securityService.findLoggedInUser();
        if(user==null) return "redirect:/user/login";

        Client client=clientRepository.getByUsername(user.getUsername());

        model.addAttribute("user",user);
        model.addAttribute("client",client);
        return "/client/profile";
    }

    @GetMapping("client/client")
    public String getClient(Model model)
    {
        List<Client> client = clientRepository.getAll();
        model.addAttribute("Client", client);

        return "client/client";
    }

    @GetMapping("client/client/{id}")
    public String viewClient(@PathVariable("id") int id,Model model){

        Client client=clientRepository.getbyClientNo(id);

        User user=userRepository.getUser(client.getUsername());

        model.addAttribute("client",client);

        model.addAttribute("user",user);

        return "client/viewProfile";
    }



    @GetMapping("client/sells")
    private String getSells(Model model)
    {   User user=securityService.findLoggedInUser();
        if(user==null) return  "redirect:/user/login";
        Client client=clientRepository.getByUsername(user.getUsername());
        List<Sells> sells = sellsRepository.getsellbyclientNo(client.getClientNo());
        System.out.println(sells);
        model.addAttribute("Sells", sells);

        return "client/viewSells";
    }

    @GetMapping("client/insurance/")

    private String getInsurances(Model model){
        List<Insurance> insurance=insuranceRepository.getAll();

        System.out.println(insurance);
        model.addAttribute("Insurance",insurance);

        return "client/insurance";
    }
    @GetMapping("client/insurance/{InsuranceId}")
    private String getInsurance(@PathVariable("InsuranceId") String InsuranceId, Model model)
    {
        Insurance insurance = insuranceRepository.getInsurancebyId((InsuranceId));
        if(insurance == null)
        {
            return "redirect:/client";
        }

        model.addAttribute("insurance", insurance);
        model.addAttribute("Policies",policies);

        return "client/viewInsurance";

    }
    @GetMapping("client/insurance/{insuranceid}/buy")

    public String buyInsurance(@PathVariable("insuranceid") String insuranceId,Model model,String success,String failed ){
        User user=securityService.findLoggedInUser();
        Insurance insurance = insuranceRepository.getInsurancebyId(insuranceId);
        if(insurance==null) return "redirect:/client/sells";
        List<Policies> policies=policiesRepository.getPoliciesByinsuranceId(insurance.getInsuranceId());
        List<Agent> agent=agentRepository.getAll();

        if(success!=null) model.addAttribute("success","Your policy is approved");
        if(failed!=null) model.addAttribute("error","Your response is failed");

        model.addAttribute("insurance", insurance);
        model.addAttribute("Policies",policies);
        model.addAttribute("user",user);
        model.addAttribute("Agents",agent);
        model.addAttribute("submiturl","/client/insurance/"+insuranceId+"/buy");

        return "client/buy";
    }
    @PostMapping("client/insurance/{insuranceid}/buy")
    public String buyInsurance(@PathVariable("insuranceid") String insuranceid, @RequestParam("agentId") int agentId, @RequestParam("amount") int amount,
                               @RequestParam("policyTerm") int policyTerm, Model model){
        User user=securityService.findLoggedInUser();
        Client client=clientRepository.getByUsername(user.getUsername());

        if(sellsRepository.getsellbyId(agentId,client.getClientNo(),insuranceid,policyTerm)!=null){
            return "redirect:/client/insurance/"+insuranceid+"/buy?failed";
        }
        long now = System.currentTimeMillis();
        Date joinDate = new Date(now);
        Sells sell=new Sells();
        sell.setClientNo(client.getClientNo());
        sell.setAgentId(agentId);
        sell.setBuyDate(joinDate);
        sell.setAmount(amount);
        sell.setPolicyTerm(policyTerm);
        sell.setInsuranceId(insuranceid);

        sellsRepository.createSells(sell);
        return "redirect:/client/sells";
    }

    @GetMapping("client/policies/{InsuranceId}/buy")
    public String getPolicy(@PathVariable("InsuranceId") String InsuranceId, Model model)
    {

        Insurance insurance=insuranceRepository.getInsurancebyId(InsuranceId);
        List<Policies> policies = policiesRepository.getPoliciesByinsuranceId(InsuranceId);


        model.addAttribute("insurance",insurance);
        model.addAttribute("policies", policies);

        return "client/viewPolicies";
    }




}
