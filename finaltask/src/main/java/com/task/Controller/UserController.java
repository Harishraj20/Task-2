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

    @GetMapping("/")
    public String homepage() {
        System.out.println("Into Home Page");
        return "Login";
    }

    @GetMapping("/Details/AddUser")
    public String addaddFormmethod(@ModelAttribute User user) {

        return "redirect:/Details/General";

    }

    @PostMapping("/users/message")
    public String addmethod(@ModelAttribute User user, @RequestParam(required = false) Integer userId, Model model,
            RedirectAttributes redirectAttributes) {
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

    @PostMapping("/users")
    public String loginUser(@RequestParam String emailId, @RequestParam String password, HttpSession session,
                            RedirectAttributes redirectAttributes) {
    
        User userExists = service.checkUserByMailId(emailId);
        String msg;
    
        if (userExists != null && userExists.getPassword().equals(password)) {
            service.updateCredentials(userExists);
            session.setAttribute("LoginUser", userExists);
    
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
    // Fetch the latest UserList from the database instead of relying on the session
    List<User> updatedUsers = service.fetchAllUsers();
    model.addAttribute("UserList", updatedUsers);
    model.addAttribute("loggedUser", session.getAttribute("LoginUser"));

    return "Details";
}



    @GetMapping("/Details")

    public String redirect(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";

        }

        return "Details";

    }

    // @GetMapping("/users/form")

    // public String redirectToSignUp(HttpSession session) {
    // if (session.getAttribute("LoginUser") == null) {
    // return "redirect:/";

    // }

    // return "General";

    // }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/users/viewInfo")

    public String viewinfos(@RequestParam String userId, @RequestParam String employeeId, Model model,
            HttpSession session) {

        int user_id = Integer.parseInt(userId);

        List<Login> logins = service.getLoginInfo(user_id);
        model.addAttribute("empId", employeeId);
        model.addAttribute("Loggedinfo", logins);

        return "LoginInfo";
    }

    @PostMapping("/users/delete")

    public String deleteUser(@RequestParam String userId, Model model, HttpSession session) {

        int user_id = Integer.parseInt(userId);
        service.deleteUserById(user_id);

        return "redirect:/users";
    }

    @GetMapping("/users/form")
    public String addOrUpdate(@RequestParam String userId, Model model) {
        if (userId.isEmpty() || userId == null) {
            model.addAttribute("user", null);
            return "General";
        } else {
            int userIdForAction = Integer.parseInt(userId);
            User userToUpdate = service.findUserById(userIdForAction);
            model.addAttribute("user", userToUpdate);
            return "General";
        }
    }

    @PostMapping("/user/update")
    public String updatemethod(@ModelAttribute User updateUser, @RequestParam String refUserID, Model model,
            RedirectAttributes redirectAttributes) {
        int paramId = Integer.parseInt(refUserID);
        String msg = service.updateUsers(updateUser, paramId);

        model.addAttribute("Message", msg);

        return "message";

    }

}