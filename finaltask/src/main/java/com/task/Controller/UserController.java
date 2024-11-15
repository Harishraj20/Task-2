package com.task.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.task.Model.Login;
import com.task.Model.User;
import com.task.Service.UserService;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/")
    public String homepage() {
        System.out.println("Into Home Page");
        return "Login";
    }

    // @GetMapping("/Details/AddUser")
    // public String addaddFormmethod(@ModelAttribute User user) {

    // return "redirect:/Details/General";

    // }

    @PostMapping("/users/add")
    public String addmethod(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Encoded user password: " + user.getPassword());

        boolean isAdded = service.addUsers(user);

        if (isAdded) {

            model.addAttribute("msg", "User \"" + user.getUserName() + "\" Created Successfully!");
            return "message";
        } else {
            redirectAttributes.addFlashAttribute("message", "User already exists with this MailId!");
            return "redirect:/users/form?userId=";
        }

    }
    // passwordEncoder.matches(password, userExists.getPassword())

    @PostMapping("/users")
    public String loginUser(@RequestParam String emailId, @RequestParam String password, HttpSession session,
            RedirectAttributes redirectAttributes) {

        User userExists = service.checkUserByMailId(emailId);
        String msg;

        if (userExists != null && passwordEncoder.matches(password, userExists.getPassword())) {
            service.updateCredentials(userExists);
            session.setAttribute("LoginUser", userExists);
            session.setAttribute("loggedInUserId", userExists.getUserId());

            List<User> users = service.fetchAllUsers();
            session.setAttribute("UserList", users);
            return "redirect:/users";
        } else {
            msg = "Invalid email or password!";
            redirectAttributes.addFlashAttribute("message", msg);
            return "redirect:/";
        }
    }

    @GetMapping("/users")
    public String showUserPage(HttpSession session, Model model) {
        if (session.getAttribute("LoginUser") == null) {
            return "redirect:/";

        }
        List<User> updatedUsers = service.fetchAllUsers();
        model.addAttribute("UserList", updatedUsers);
        model.addAttribute("loggedUser", session.getAttribute("LoginUser"));

        return "Details";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/users/viewInfo")

    public String viewinfos(@RequestParam String userId, @RequestParam String employeeId, Model model,
            HttpSession session) {

        if (session.getAttribute("LoginUser") == null) {
            return "redirect:/";

        }

        int user_id = Integer.parseInt(userId);

        List<Login> logins = service.getLoginInfo(user_id);
        model.addAttribute("empId", employeeId);
        model.addAttribute("Loggedinfo", logins);

        return "LoginInfo";
    }

    @PostMapping("/users/delete")

    public String deleteUser(@RequestParam String userId, Model model, HttpSession session) {

        if (session.getAttribute("LoginUser") == null) {
            return "redirect:/";

        }
        int user_id = Integer.parseInt(userId);
        service.deleteUserById(user_id);

        return "redirect:/users";
    }

    @GetMapping("/users/form")
    public String addOrUpdate(@RequestParam String userId, Model model, HttpSession session) {

        if (session.getAttribute("LoginUser") == null) {
            return "redirect:/";

        }
        if (userId.isEmpty() || userId == null) {
            model.addAttribute("user", null);
            return "General";
        } else {
            int userIdForAction = Integer.parseInt(userId);
            User userToUpdate = service.findUserById(userIdForAction);
            model.addAttribute("LoggedInId", session.getAttribute("loggedInUserId"));
            model.addAttribute("user", userToUpdate);
            return "General";
        }
    }

    @PostMapping("/users/update")
    public String updatemethod(@ModelAttribute User updateUser, @RequestParam String refUserID, Model model,
            RedirectAttributes redirectAttributes, HttpSession session) {

        if (session.getAttribute("LoginUser") == null) {
            return "redirect:/";

        }
        int paramId = Integer.parseInt(refUserID);

        if (service.updateUsers(updateUser, paramId)) {
            String msg = "User updated successfully!";
            model.addAttribute("Message", msg);
            return "message";
        } else {
            String msg = "Mail ID is already taken by someone!";
            redirectAttributes.addFlashAttribute("message", msg);
            return "redirect:/users/form?userId=" + refUserID;
        }
    }

    @GetMapping("/users/inactiveUsers")
    public String view(Model model, HttpSession session) {
        if (session.getAttribute("LoginUser") == null) {
            return "redirect:/";

        }
        List<User> usersList = service.fetchAllUsers();
        System.out.println(usersList);

        model.addAttribute("UserList", usersList);

        return "InactiveUsers";

    }

}