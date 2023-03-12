package MiniDFlow.repository.entity;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DocumentVersion")
public class DocumentVersion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documentVersionId")
    private int id;

    @JoinColumn(name = "documentId")
    @ManyToOne
    private Document documentId;

    @JoinColumn(name = "versionAuthor")
    @ManyToOne
    private Author versionAuthor;

    @Column(name = "content")
    private byte content;

    public DocumentVersion() {
    }

    public DocumentVersion(Document documentId, Author versionAuthor, byte content) {
        this.documentId = documentId;
        this.versionAuthor = versionAuthor;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Document getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Document documentId) {
        this.documentId = documentId;
    }

    public Author getVersionAuthor() {
        return versionAuthor;
    }

    public void setVersionAuthor(Author versionAuthor) {
        this.versionAuthor = versionAuthor;
    }

    public byte getContent() {
        return content;
    }

    public void setContent(byte content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentVersion that)) return false;
        return id == that.id && content == that.content && documentId.equals(that.documentId) && versionAuthor.equals(that.versionAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentId, versionAuthor, content);
    }
}
