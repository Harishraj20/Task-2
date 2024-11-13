package com.task.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.task.Model.User;
import com.task.Service.UserService;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homepage() {
        System.out.println("Into Home Page");
        return "Login";
    }

    @GetMapping("/Details/AddUser")
    public String addaddFormmethod(@ModelAttribute User user) {

        return "redirect:/Details/Signup";

    }

    @PostMapping("/Details/Signup/addUser")
    public String addmethod(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        String error;
        User userExists = service.checkUserByMailId(user.getEmailId());

        if (userExists == null) {
            String msg = service.addUsers(user);
            model.addAttribute("msg", msg);
            return "message";

        } else {
            error = "User already exists with this MailId!";
            redirectAttributes.addFlashAttribute("message", error);
            return "redirect:/Details/Signup";
        }

    }

    @PostMapping("/LoginUser")
    public String loginuser(@RequestParam String emailId, @RequestParam String password, HttpSession session,
            RedirectAttributes redirectAttributes) {

        User userExists = service.checkUserByMailId(emailId);
        String msg;

        if (userExists != null && userExists.getPassword().equals(password)) {
            service.updateCredentials(userExists);
            session.setAttribute("user", userExists);

            List<User> users = service.fetchAllUsers();
            System.out.println(users);
            redirectAttributes.addFlashAttribute("UserList", users);
            redirectAttributes.addFlashAttribute("loggedUser", userExists);

            return "redirect:/Details";
        } else {
            msg = "Invalid email or password!";
            redirectAttributes.addFlashAttribute("message", msg);
            return "redirect:/";
        }
    }

    @GetMapping("/Details")

    public String redirect(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";

        }

        return "Details";

    }

    @GetMapping("/Details/Signup")

    public String redirectToSignUp(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";

        }

        return "Signup";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}