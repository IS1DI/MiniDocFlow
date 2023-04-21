package MiniDFlow.POJO;


import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class DocumentPOJO implements Serializable {
    private String name;
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
