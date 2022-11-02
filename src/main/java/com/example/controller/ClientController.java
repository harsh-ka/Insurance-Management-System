package com.example.controller;

import com.example.dao.*;
import com.example.models.Client;
import com.example.models.Insurance;
import com.example.models.Policies;
import com.example.models.Sells;
import com.example.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.models.User;
import com.example.models.Client;
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


    @GetMapping("/client/dashboard")
    public String getDashboard(Model model)
    {
        User user=securityService.findLoggedInUser();
        Client client=clientRepository.getByUsername(user.getUsername());

        model.addAttribute("client",client);
        return "/client/dashboard";
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



    @GetMapping("client/sells/{clientNo}")
    private String getClient(@PathVariable("clientNo") int clientNo, Model model)
    {
        List<Sells> sells = sellsRepository.getsellbyclientNo(clientNo);
        System.out.println(sells);
        if(sells.size() == 0)
        {
            return "redirect:/client/client";
        }
        model.addAttribute("Sells", sells);

        return "client/viewSells";
    }

    @GetMapping("client/insurance/")

    private String getInsurances(Model model){
        List<Insurance> insurance=insuranceRepository.getAll();
        model.addAttribute("insurance",insurance);

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

        model.addAttribute("Insurance", insurance);

        return "client/viewInsurance";

    }

    @GetMapping("client/policies/{InsuranceId}")
    public String getPolicy(@PathVariable("InsuranceId") String InsuranceId, Model model)
    {
        Insurance insurance=insuranceRepository.getInsurancebyId(InsuranceId);
        List<Policies> policies = policiesRepository.getPoliciesByinsuranceId(InsuranceId);

        model.addAttribute("insurance",insurance);
        model.addAttribute("policies", policies);

        return "client/viewPolicies";
    }


}
