package MiniDFlow.repository.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Author")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorId")
    private int id;

    @Column(name = "username")
    private String userName;

    @Column(name = "login")
    private String login;


    @Column(name = "password")
    private String password;

    public Author(String login, String password, String userName) {
        this.login = login;
        this.password = password;
        this.userName = userName;
    }

    public Author() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return id == author.id && userName.equals(author.userName) && login.equals(author.login) && password.equals(author.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, login, password);
    }
}
