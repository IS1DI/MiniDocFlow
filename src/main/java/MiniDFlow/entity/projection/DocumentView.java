package MiniDFlow.entity.projection;

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
    private int lengthOfFile;
    private int version;
    private boolean isExist;

    public DocumentView(int documentId,String documentName, String author, int lengthOfFile,int version,boolean isExist) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.author = author;
        this.lengthOfFile = lengthOfFile;
        this.version = version;
        this.isExist = isExist;
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

    public int getLengthOfFile() {
        return lengthOfFile;
    }

    public void setLengthOfFile(int lengthOfFile) {
        this.lengthOfFile = lengthOfFile;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

}
