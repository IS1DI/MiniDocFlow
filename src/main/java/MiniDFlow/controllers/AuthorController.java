package MiniDFlow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @GetMapping("/{username}")
    public String authorByUsername(@PathVariable("username") String username){
        return "";
    }
}
