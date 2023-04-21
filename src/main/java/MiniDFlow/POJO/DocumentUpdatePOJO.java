package MiniDFlow.POJO;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class DocumentUpdatePOJO implements Serializable {
    private MultipartFile file;
    private int documentId;

    public DocumentUpdatePOJO(int documentId) {
        this.documentId = documentId;
    }

    public DocumentUpdatePOJO() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }
}
