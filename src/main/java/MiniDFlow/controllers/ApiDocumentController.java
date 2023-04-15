package MiniDFlow.controllers;

import MiniDFlow.POJO.Document;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;


@RestController
@RequestMapping("/api/doc")
public class ApiDocumentController {
    @PostMapping("/new")
    public String newDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("documentIntroNumber") String documentIntroNumber
            ) throws IOException {
        Document document = new Document();
        document.setDocumentIntroNumber(documentIntroNumber);
        document.setName(name);
        return document.getName() + "\n" + document.getDocumentIntroNumber() + "\n" + file.getOriginalFilename() + "\n" + file.getSize() + "\n" + Arrays.toString(file.getBytes());
    }


}
