package MiniDFlow.controllers;

import MiniDFlow.POJO.DocumentPOJO;
import MiniDFlow.POJO.DocumentUpdatePOJO;
import MiniDFlow.entity.projection.DocumentView;
import MiniDFlow.service.AuthorService;
import MiniDFlow.service.DocumentService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/doc")
public class ApiDocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private AuthorService authorService;
    @PostMapping("/new")
    public String newDocumentSave(
            @ModelAttribute("documentPOJO") DocumentPOJO documentPOJO,
            Authentication authentication
            ) throws IOException {
        documentService.createNewDocument(documentPOJO,authorService.findByUsername(authentication.getName()));
        return "redirect:/doc/main";
    }

    @GetMapping("/new")
    public String newDocumentView(Model model){
        model.addAttribute(new DocumentPOJO());
        return "fileUploadView";
    }


    @PostMapping("/update")
    public String updateDocumentById(@ModelAttribute("documentUpdatePOJO") DocumentUpdatePOJO documentUpdatePOJO, Authentication authentication) throws IOException {
        documentService.updateDocumentById(documentUpdatePOJO,authorService.findByUsername(authentication.getName()));
        return "redirect:/doc/main";
    }
    @PostMapping("{id}/update")
    public String updateDocumentByParamId(@PathVariable("id")int id,@ModelAttribute("documentUpdatePOJO") DocumentUpdatePOJO documentUpdatePOJO, Authentication authentication) throws IOException {
        documentUpdatePOJO.setDocumentId(id);
        documentService.updateDocumentById(documentUpdatePOJO,authorService.findByUsername(authentication.getName()));
        return "redirect:/doc/main";
    }

    @GetMapping("{id}/update")
    public String getUpdateViewById(@PathVariable("id") int id,Model model){
        DocumentView documentView = documentService.getDocById(id,-1);
        model.addAttribute("documentPOJO",new DocumentUpdatePOJO(documentView.getDocumentId()));
        model.addAttribute("docName",documentView.getDocumentName());
        return "updateView";
    }
    @GetMapping("/update")
    public String getUpdateView(Model model){
        model.addAttribute(new DocumentUpdatePOJO());
        return "updateByIdView";
    }

    @GetMapping("/{id}")
    public String getFileById(Model model,
                              @PathVariable("id") int id){
        model.addAttribute("doc",documentService.getDocById(id,-1));
        return "document";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id,
                         @RequestParam("dEx") String documentExternNumber){
        documentService.deleteDocumentById(id,documentExternNumber);
        return "redirect:/doc/main";
    }

    @GetMapping("/{id}/delete")
    public String getDeleteView(@PathVariable("id") int id, Model model){
        model.addAttribute("doc",documentService.getDocById(id,-1));
        return "deleteView";
    }

    @PostMapping("/{id}")
    public String download(@PathVariable int id,
                           @RequestParam(required = false,defaultValue = "-1",name = "ver") int ver,
                           HttpServletResponse response){
        byte[] file = documentService.getFile(id,ver);
        try{
            InputStream inputStream = new ByteArrayInputStream(file);
            IOUtils.copy(inputStream,response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/doc/main";
    }
    @GetMapping("/main")
    public String mainPage(Model model){
        model.addAttribute(documentService.getAllDocViews());
        return "main";
    }

}
