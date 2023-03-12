package MiniDFlow.repository;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "registerCard")
public class RegisterCard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regCardId")
    private int id;


    @JoinColumn(name = "documentId")
    @OneToOne
    private Document documentId;

    @Column(name = "documentIntroNumber")
    private String documentIntroNumber;

    @Column(name = "documentExternNumber")
    private String documentExternNumber;

    @Column(name = "dateIntro")
    private Instant dateIntro;

    @Column(name = "dateExtern")
    private Instant dateExtern;


    public RegisterCard(Document documentId, String documentIntroNumber, String documentExternNumber, Instant dateIntro, Instant dateExtern) {
        this.documentId = documentId;
        this.documentIntroNumber = documentIntroNumber;
        this.documentExternNumber = documentExternNumber;
        this.dateIntro = dateIntro;
        this.dateExtern = dateExtern;
    }

    public RegisterCard() {
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

    public String getDocumentIntroNumber() {
        return documentIntroNumber;
    }

    public void setDocumentIntroNumber(String documentIntroNumber) {
        this.documentIntroNumber = documentIntroNumber;
    }

    public String getDocumentExternNumber() {
        return documentExternNumber;
    }

    public void setDocumentExternNumber(String documentExternNumber) {
        this.documentExternNumber = documentExternNumber;
    }

    public Instant getDateIntro() {
        return dateIntro;
    }

    public void setDateIntro(Instant dateIntro) {
        this.dateIntro = dateIntro;
    }

    public Instant getDateExtern() {
        return dateExtern;
    }

    public void setDateExtern(Instant dateExtern) {
        this.dateExtern = dateExtern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterCard that)) return false;
        return id == that.id && documentIntroNumber.equals(that.documentIntroNumber) && documentExternNumber.equals(that.documentExternNumber) && documentId.equals(that.documentId) && dateIntro.equals(that.dateIntro) && Objects.equals(dateExtern, that.dateExtern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentId, documentIntroNumber, documentExternNumber, dateIntro, dateExtern);
    }
}
