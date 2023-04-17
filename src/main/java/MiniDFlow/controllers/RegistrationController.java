package MiniDFlow.controllers;

import MiniDFlow.POJO.UserRegForm;
import MiniDFlow.security.JdbcAuthorService;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    @Autowired
    JdbcAuthorService userDetailsManger;
    @GetMapping(value = "/hello")
    public ResponseEntity<?> startPage(){
        return new ResponseEntity<>("hello",HttpStatus.OK);
    }

    @PostMapping(value = "/reg")
    public ResponseEntity<?> register(@RequestParam String username, @RequestParam String pass) {
        userDetailsManger.createUser(new UserRegForm(username,pass));
        return new ResponseEntity<>("registered", HttpStatus.OK);
    }

}
