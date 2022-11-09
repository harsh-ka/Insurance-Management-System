package com.example.controller;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import com.example.dao.*;
import com.example.models.Admin;
import com.example.models.Client;
import com.example.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import com.example.models.User;
import com.example.services.SecurityService;
import com.example.services.EmailService;
import com.example.dao.EmployeeRepository;
import javax.validation.Valid;

@Controller

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
    AdminRepository adminRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/user/login")
    public String login(Model model, String error, String logout, String emailSent, String resetPassword,String account_created) {
        securityService.autoLogout();

        if(adminRepository.getByUsername("admin")==null){
            Admin admin=new Admin();
            User user=new User();

            long now = System.currentTimeMillis();
            Date joinDate = new Date(now);

            String token= UUID.randomUUID().toString();
            user.setUsername("admin");
            user.setPasswordHash("admin");
            user.setRole("Admin");
            user.setEmailAddress("admin@gmai.com");
            user.setFirstName("Harsh");
            user.setLastName("Sharma");
            user.setDateOfBirth(joinDate);
            user.setGender("Male");
            user.setAddress("N/A");
            user.setToken(token);

            admin.setAdmin_email(user.getEmailAddress());
            admin.setAdmin_id(1);
            admin.setAdmin_name("Harsh");
            admin.setUsername("admin");
            admin.setContactNo("1234567891");

            userRepository.createUser(user);
            adminRepository.createAdmin(admin);
        }

        if(account_created!=null)
            model.addAttribute("success","account created succesfully");
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
       System.out.println(user);
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
    @GetMapping("user/logout")
    private  String logout(){
        System.out.println("This is inside logout function");
        return "redirect:/user/login";
    }
    @GetMapping("/user/signup")
    public String addStudent(Model model) {

        User user=new User();
        List<Employee> employees=employeeRepository.getAll();
        user.setRole("Client");

        model.addAttribute("user", user);
        model.addAttribute("submiturl", "/user/signup/");
        model.addAttribute("employees",employees);
        return "template/createUser";
    }

    @PostMapping("/user/signup")
    private String addClient(@ModelAttribute("user") User user,@RequestParam("clientNo") int clientNo,
                             @RequestParam("employeeId") String employeeId,
                             @RequestParam("middleName") String middleName,
                             @RequestParam("contact") String contact,
                             Model model,BindingResult bindingResult){

        if(userRepository.getUser(user.getUsername())!=null){
            bindingResult.rejectValue("username", "Duplicate.username");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("error","Please Enter Unique username!!!!!!!!!!!!!!!!");
            model.addAttribute("submiturl","/user/signup/");
            return "redirect:/user/signup";
        }

        String token=UUID.randomUUID().toString();
        user.setRole("Client");
        user.setToken(token);

        Client client=new Client();
        client.setClientNo(clientNo);
        client.setFirstName(user.getFirstName());
        client.setLastName(user.getLastName());
        client.setMiddleName(middleName);
        client.setUsername(user.getUsername());
        client.setClientEmail(user.getEmailAddress());
        client.setLandMark(user.getAddress());
        client.setEmployeeId(employeeId);
        client.setClientContact(contact);
        client.setCity(user.getAddress());
        client.setHouseNo(1);

        int user_affected=userRepository.createUser(user);
        int client_affected=clientRepository.createClient(client);

        String val;
        if(user_affected!=0 && client_affected!=0){
            val="?account_created";
        }
        else{
            val="?error";
        }
        emailService.sendInitialMail(user);
        return "redirect:/template/login"+val;
    }


   /* @PostMapping("/user/signup/")
    public String UserRegister(@ModelAttribute("user") User user,@RequestParam("password") String password, Model model, BindingResult bindingResult){
        if(userRepository.getUser(user.getUsername())!=null){
            bindingResult.rejectValue("username", "Duplicate.username");
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("error","Please Enter Unique username!!!!!!!!!!!!!!!!");
            model.addAttribute("submiturl","/user/signup/");
            return "template/createUser";
        }
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        user.setPasswordHash(password);


        userRepository.createUser(user);



        return "redirect:/template/login";
    }
*/
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

        //emailService.sendForgotMail(user);
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
