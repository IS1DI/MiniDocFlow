package MiniDFlow.controllers;

import MiniDFlow.POJO.UserDetailsForm;
import MiniDFlow.POJO.UserRegForm;
import MiniDFlow.security.JdbcAuthorService;
import javax.validation.Valid;

import MiniDFlow.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    @Autowired
    private JdbcAuthorService userDetailsManger;
    @Autowired
    private AuthorService authorService;
    @GetMapping("/")
    public String redirectToMain(){
        return "redirect:/doc/main";
    }

    @GetMapping(value = "/reg")
    public String getRegisterPage(Model model){
        UserRegForm userRegForm = new UserRegForm();
        model.addAttribute("userRegForm",userRegForm);
        return "register";
    }

    @PostMapping(value = "/reg")
    public String register(@ModelAttribute("userRegForm") @Valid UserRegForm userRegForm,
                           BindingResult result) {
        if(result.hasErrors()){
            return "register";
        }
        if(authorService.exist(userRegForm.getUsername())) {
            result.addError(new ObjectError("userRegForm.username","user with this username exist"));
            return "register";
        }else {
            userDetailsManger.createUser(new UserDetailsForm(userRegForm));
        }
        return "redirect:/doc/main";
    }

}
