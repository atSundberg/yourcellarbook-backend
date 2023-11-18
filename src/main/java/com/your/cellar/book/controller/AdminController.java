package com.your.cellar.book.controller;

import com.your.cellar.book.entity.UserLog;
import com.your.cellar.book.service.UserLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private UserLogService userLogService;

    @Autowired
    public AdminController(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @GetMapping
    public String showLogs(Model model) {
        log.info("Inside /admin.showLogs: {}", model);
        List<UserLog> logs = userLogService.getAllLogs();
        model.addAttribute("logs", logs);
        return "dashboard";
    }

}
