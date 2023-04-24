package MiniDFlow.controllers;

import MiniDFlow.service.AuthorService;
import MiniDFlow.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private DocumentService documentService;
    @GetMapping("/{username}")
    public String authorByUsername(@PathVariable("username") String username, Model model, Principal principal){
        model.addAttribute("author",principal.getName());
        model.addAttribute("username", username);
        model.addAttribute("documentViewList", documentService.getAllDocViewsByAuthor(authorService.findByUsername(username)));
        return "authorView";
    }
}
