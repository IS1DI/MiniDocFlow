package MiniDFlow.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Author author;

    public Authority( String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public Authority() {
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
        if (!(o instanceof Authority authority)) return false;
        return id == authority.id && Objects.equals(name, authority.name) && Objects.equals(author, authority.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author);
    }
}
