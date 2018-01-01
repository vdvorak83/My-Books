package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.services.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/users")
    public String getMainAdminPage(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("users", adminService.getAllUsers());

        return "admin";
    }

    @GetMapping("/password/temp/{user-id}")
    public String getNewPasswordOfUser(@ModelAttribute("model") ModelMap model, @PathVariable("user-id") Integer id) {
        adminService.createTempPassword(id);

        return "temp-password";
    }
}
