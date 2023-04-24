package MiniDFlow.POJO;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class DocumentPOJO implements Serializable {
    @NotEmpty(message = "name of document is empty")
    @Size(min=3,message = "length must be higher than 3")
    private String name;
    @NotEmpty(message = "intro number is empty")
    @Size(min = 3,max= 30,message = "must be in range from 3 to 30")
    private String documentIntroNumber;
    private MultipartFile file;
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentIntroNumber() {
        return documentIntroNumber;
    }

    public void setDocumentIntroNumber(String documentIntroNumber) {
        this.documentIntroNumber = documentIntroNumber;
    }
}
