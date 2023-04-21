package MiniDFlow.controllers;

import MiniDFlow.POJO.UserDetailsForm;
import MiniDFlow.POJO.UserRegForm;
import MiniDFlow.security.JdbcAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    @Autowired
    JdbcAuthorService userDetailsManger;

    @GetMapping(value = "/reg")
    public String getRegisterPage(Model model){
        UserRegForm userRegForm = new UserRegForm();
        model.addAttribute("userRegForm",userRegForm);
        return "register";
    }

    @PostMapping(value = "/reg")
    public String register(@ModelAttribute("userRegForm") UserRegForm user) {
        userDetailsManger.createUser(new UserDetailsForm(user));
        return "redirect:/doc/main";
    }

}
