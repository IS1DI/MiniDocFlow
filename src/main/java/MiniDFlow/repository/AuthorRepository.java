package MiniDFlow.repository;

import MiniDFlow.repository.entity.Author;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    private Session session;

    public AuthorRepository(Session session) {
        this.session = session;
    }

    /**
     * Создание нового пользователя
     *
     * @param login:    логин пользователя
     * @param password: пароль пользователя
     * @param username: имя пользователя
     */
    public void create(String login, String password, String username) {
        Author author = new Author(login, password, username);
        session.persist(author);
    }

    /**
     * Получение информации о пользователе по id
     *
     * @param id: идентификатор пользователя
     * @return объект Author, соответствующий заданному идентификатору
     */
    public Author getById(Integer id) {
        return session.find(Author.class, id);
    }

    /**
     * Получение информации о пользователе по userName
     *
     * @param username: имя пользователя
     * @return объект Author, соответствующий заданному имени пользователя
     */
    public Optional<Author> getByUsername(String username) {
        String hql = "FROM Author WHERE username = :username";
        TypedQuery<Author> query = session.createQuery(hql, Author.class);
        query.setParameter("username", username);
        List<Author> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }

    /**
     * Изменение имени пользователя по id
     *
     * @param id:          идентификатор пользователя
     * @param newUsername: новое имя пользователя
     */
    public void updateUsernameById(Integer id, String newUsername) {
        Author author = getById(id);
        if (author != null) {
            String hql = "UPDATE Author SET username = :newUsername WHERE id = :id";
            TypedQuery<Author> query = session.createQuery(hql, Author.class);
            query.setParameter("newUsername", newUsername);
            query.setParameter("id", id);
            query.executeUpdate();
        }
    }


    /**
     * Изменение имени пользователя по entity (в entity уже содержится id)
     *
     * @param author: объект Author с новым именем пользователя и существующим идентификатором
     */
    public void updateUsernameByEntity(Author author) {
        session.merge(author);
    }

    /**
     * Удаление пользователя из базы по id
     *
     * @param id: идентификатор пользователя
     */
    public void deleteById(Integer id) {
        Author author = getById(id);
        if (author != null) {
            session.remove(author);
        }
    }

    /**
     * Удаление пользователя из базы по entity (в entity уже содержится id)
     *
     * @param author: объект Author с заданным идентификатором, который нужно удалить
     */
    public void deleteByEntity(Author author) {
        session.remove(session.contains(author) ? author : session.merge(author));
    }
}
