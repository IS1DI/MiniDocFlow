package MiniDFlow.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiniDocFlowRESTController {

    @GetMapping(value = "/hello")
    public String startPage(){
        return "hello";
    }

}
