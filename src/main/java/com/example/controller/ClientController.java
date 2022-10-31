package com.example.controller;

import com.example.dao.ClientRepository;
import com.example.dao.InsuranceRepository;
import com.example.dao.PoliciesRepository;
import com.example.dao.SellsRepository;
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

import java.util.List;

@Controller
@Transactional
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


    @GetMapping("/client/dashboard")
    public String getDashboard()
    {
        return "/client/dashboard";
    }

    @GetMapping("client/client")
    public String getClient(Model model)
    {
        List<Client> client = clientRepository.getAll();
        model.addAttribute("Client", client);

        return "client/client";
    }

    @GetMapping("client/sells/{clientNo}")
    private String getClient(@PathVariable("clientNo") int clientNo, Model model)
    {
        List<Sells> sells = sellsRepository.getsellbyclientNo(clientNo);
        if(sells == null)
        {
            return "redirect:/client/client";
        }
        model.addAttribute("Sells", sells);

        return "client/viewSells";
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
        List<Policies> policies = policiesRepository.getPoliciesByinsuranceId(InsuranceId);

        model.addAttribute("Policies", policies);

        return "client/viewPolicies";
    }


}
