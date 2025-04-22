package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add")
    public String saveNewUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "edit-user";
    }

    @PostMapping("/edit")
    public String saveEditedUser(@RequestParam("id") Long id, @ModelAttribute User updatedUser, Model model) {
        User user = userService.findById(id);
        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
        userService.update(user);

        return "redirect:/users";
    }
}
