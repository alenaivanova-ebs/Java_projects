package com.runner.controller;

import com.runner.controller.util.PersonValidator;
import com.runner.dao.model.Person;
import com.runner.security.PersonDetails;
import com.runner.service.AdminService;
import com.runner.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class SecurityController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final AdminService adminService;

    @Autowired
    public SecurityController(PersonValidator personValidator, RegistrationService registrationService, AdminService adminService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.adminService = adminService;
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.toString();
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }


    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "auth/registration";
        registrationService.register(person);
        return "redirect:/auth/login";
    }

    @GetMapping("/admin")
    public String adminPage() {
        //address is available for all users, the content is available for admins only
        adminService.doAdminStuff();
        return "admin";
    }
}


