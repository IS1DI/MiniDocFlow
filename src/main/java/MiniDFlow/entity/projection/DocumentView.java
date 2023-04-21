package MiniDFlow.entity.projection;

import java.time.Instant;

public class DocumentView {
    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }



    private int documentId;
    private String documentName;
    private String author;
    private String documentIntroNumber;
    private int version;
    private boolean isExist;
    private Instant dateIntro;

    public DocumentView(int documentId,
                        String documentName,
                        String author,
                        int version,
                        boolean isExist,
                        Instant dateIntro,
                        String documentIntroNumber) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.author = author;
        this.version = version;
        this.isExist = isExist;
        this.dateIntro = dateIntro;
        this.documentIntroNumber = documentIntroNumber;
    }


    public DocumentView() {
    }
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }
    public Instant getDateIntro() {
        return dateIntro;
    }

    public void setDateIntro(Instant dateIntro) {
        this.dateIntro = dateIntro;
    }
    public String getDocumentIntroNumber() {
        return documentIntroNumber;
    }

    public void setDocumentIntroNumber(String documentIntroNumber) {
        this.documentIntroNumber = documentIntroNumber;
    }

}
