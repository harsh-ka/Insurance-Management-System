package com.example.controller;

import java.util.List;
import java.util.UUID;

import com.example.dao.ClientRepository;
import com.example.dao.EmployeeRepository;
import com.example.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import com.example.models.User;
import com.example.dao.UserRepository;
import com.example.services.SecurityService;
import com.example.services.EmailService;
import com.example.dao.EmployeeRepository;
import javax.validation.Valid;

@Controller
@Transactional
public class LoginController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/user/login")
    public String login(Model model, String error, String logout, String emailSent, String resetPassword) {
        if (error != null)
            model.addAttribute("error", "Invalid username or password");

        if (logout != null)
            model.addAttribute("success", "You have been logged out successfully !");

        if (emailSent != null)
            model.addAttribute("success", "Your password reset mail has been sent");

        if (resetPassword != null)
            model.addAttribute("success", "Password reset successful !");

        return "template/login";
    }
    @GetMapping("/welcome")
    public String user() {
        User user = securityService.findLoggedInUser();
     //   System.out.println(employeeRepository.getAll());
      //  System.out.println(user);
        if (user == null)
            return "redirect:/user/login?error";

        if (user.getRole().equals("Admin")) {
            return "redirect:/admin/dashboard";
        }
        else if (user.getRole().equals("Client"))
            return "redirect:/client/dashboard";

        else if (user.getRole().equals("Agent"))
            return "redirect:/agent/dashboard";

        else if (user.getRole().equals("Employee"))
            return "redirect:/employee/dashboard";

        else
            return "redirect:/user/login?error";

    }
    @GetMapping("/user/signup")
    public String addStudent(Model model) {

        User user=new User();
        model.addAttribute("user", user);
        model.addAttribute("submiturl", "/user/signup/");
        return "template/createUser";
    }

    @PostMapping("/user/signup/")
    public String UserRegister(@ModelAttribute("user") User user, Model model, BindingResult bindingResult){
        System.out.println("This is inside post mapping ");
        if(userRepository.getUser(user.getUsername())!=null){
            bindingResult.rejectValue("username", "Duplicate.username");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("submiturl","/user/signup/");
            return "redirect:/template/createUser";
        }
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setPasswordHash(user.getUsername());

        userRepository.createUser(user);

       //emailService.sendInitialMail(user);

        return "redirect:/template/login";
    }

    @GetMapping("/user/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("username", "");
        model.addAttribute("submiturl", "/user/forgot-password");
        return "template/forgotPassword";
    }

    @PostMapping("/user/forgot-password")
    public String forgotPassword(@RequestParam("username") String username, Model model) {

        User user = userRepository.getUser(username);

        if (user == null) {
            model.addAttribute("error", "The username does not exist");
            model.addAttribute("submiturl", "/user/forgot-password");
            return "template/forgotPassword";
        }

        String token = UUID.randomUUID().toString();
        user.setToken(token);
        userRepository.updateToken(user);

        emailService.sendForgotMail(user);
        return "redirect:/user/login?emailSent";
    }

    @GetMapping("/user/reset-password")
    public String resetPassword(String token, Model model) {

        User user = userRepository.getUserByToken(token);
        if (user == null)
            return "redirect:/";

        model.addAttribute("submiturl", "/user/reset-password?token=" + token);
        return "template/resetPassword";

    }

    @PostMapping("/user/reset-password")
    public String resetPassword(String token, @RequestParam("password") String password,
                                @RequestParam("confirmPassword") String confirmPassword, Model model) {

        User user = userRepository.getUserByToken(token);
        if (user == null)
            return "redirect:/";

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "The passwords do not match");
            model.addAttribute("submiturl", "/user/reset-password?token=" + token);
            return "template/resetPassword";
        }

        userRepository.changePassword(user.getUsername(), password);
        return "redirect:/user/login?resetPassword";
    }

    @GetMapping("/user/change-password")
    public String changePassword(Model model) {

        User user = securityService.findLoggedInUser();
        if (user == null)
            return "redirect:/";

        model.addAttribute("submiturl", "/user/change-password");
        return "template/changePassword";
    }

    @PostMapping("/user/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
                                 Model model) {

        User user = securityService.findLoggedInUser();
        if (user == null)
            return "redirect:/";

        if (!bCryptPasswordEncoder.matches(oldPassword, user.getPasswordHash())) {
            model.addAttribute("error", "Invalid Password");
            model.addAttribute("submiturl", "/user/change-password");
            return "template/changePassword";
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "The passwords do not match");
            model.addAttribute("submiturl", "/user/change-password");
            return "template/changePassword";
        }

        userRepository.changePassword(user.getUsername(), newPassword);
        securityService.autoLogout();
        return "redirect:/user/login?resetPassword";
    }
}
