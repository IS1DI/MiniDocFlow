package MiniDFlow.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MiniDocFlowRESTController {

    @GetMapping(value = "/hello")
    public String startPage(){
        return "hello";
    }
}
