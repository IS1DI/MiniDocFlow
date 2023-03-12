package MiniDFlow.repository;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Document")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documentId")
    private int id;

    @Column(name = "documentName")
    private String name;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    public Document() {
    }

    public Document(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document document)) return false;
        return id == document.id && name.equals(document.name) && author.equals(document.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author);
    }
}
