package com.your.cellar.book.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String getHome() {
        return "Hello, I'm home";
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getAdmin() {
        return "Hello, I'm Admin";
    }

}
