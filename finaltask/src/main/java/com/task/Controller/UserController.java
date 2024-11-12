package com.task.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/addUser")
    public String addmethod(@ModelAttribute User user) {

        String msg = service.addUsers(user);
        System.out.println(msg);

        return "Login";

    }

    @PostMapping("/LoginUser")
    public ModelAndView loginuser(@RequestParam String emailId, @RequestParam String password) {
        ModelAndView mv = new ModelAndView("message");
        User userExists = service.checkUserByMailId(emailId);
        String msg;
        if (userExists != null && userExists.getPassword().equals(password)) { 
            service.updateCredentials(userExists);
            msg = "LoggedIn successfully";
        } else {
            msg = "Invalid email or password!";
        }
        mv.addObject("msg", msg);
        return mv;
    }
    

}