package MiniDFlow.controllers;

import MiniDFlow.POJO.Page;
import MiniDFlow.entity.projection.DocumentView;
import MiniDFlow.security.JdbcAuthorService;
import MiniDFlow.service.AuthorService;
import MiniDFlow.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/doc/main")
public class PageController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    JdbcAuthorService userDetailsManager;
    @GetMapping
    public String mainPage(Model model, Principal principal,
                           @RequestParam(value = "p",required = false,defaultValue = "1") int page,
                           @RequestParam(value = "l",required = false,defaultValue = "10") int limit){
        Page<DocumentView> pageView = documentService.getPage(page,limit);
        model.addAttribute("docsCount",pageView.getMaxResults());
        model.addAttribute("user",userDetailsManager.loadUserByPrincipal(principal));
        model.addAttribute("curPage",pageView.getCurPage());
        model.addAttribute("limitPage",pageView.getLimitPage());
        model.addAttribute("lastPage",pageView.getLastPage());
        model.addAttribute("documentViewList",pageView.getContent());
        return "main";
    }
}
