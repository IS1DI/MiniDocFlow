package MiniDFlow.repository;

import MiniDFlow.repository.entity.Author;
import com.sun.istack.NotNull;
import jakarta.persistence.TypedQuery;
import javassist.bytecode.DuplicateMemberException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.NoSuchElementException;
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
     * @param author Должны быть заполнены все поля
     *               !!!КРОМЕ ID!!!
     * @throws DuplicateMemberException если такой login существует в бд
     */
    public void create(Author author) throws DuplicateMemberException {
        try {
            session.persist(author);
        }catch (Exception ex){
            throw new DuplicateMemberException("author с таким login существует");
        }
    }

    /**
     * Получение информации о пользователе по id
     *
     * @param id: идентификатор пользователя
     * @return объект Author, соответствующий заданному идентификатору ИЛИ null, если по такому id нет сущности
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
        String hql = "FROM Author WHERE userName = :username";
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
     * @throws NoSuchElementException если не существует пользователя с таким id
     */
    public void updateUsernameById(Integer id, String newUsername) throws NoSuchElementException {
        Author author = getById(id);
        if (author != null) {
            String hql = "UPDATE Author SET userName = :newUsername WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("newUsername", newUsername);
            query.setParameter("id", id);
            query.executeUpdate();
        }else{
            throw new NoSuchElementException("incorrect id");
        }
    }


    /**
     * Изменение имени пользователя по entity (в entity уже содержится id)
     *
     * @param author: объект Author с новым именем пользователя и существующим идентификатором
     */
    public void updateUsernameByEntity(@NotNull Author author, String newUsername) {
        author.setUserName(newUsername);
        session.merge(author);
    }

    /**
     * Удаление пользователя из базы по id
     *
     * @param id: идентификатор пользователя
     * @throws NoSuchElementException если не существует пользователя с таким id
     */
    public void deleteById(Integer id) throws NoSuchElementException {
        Author author = getById(id);
        if (author != null) {
            session.remove(author);
        }else{
            throw new NoSuchElementException("incorrect id");
        }
    }

    /**
     * Удаление пользователя из базы по entity (в entity уже содержится id)
     *
     * @param author: объект Author с заданным идентификатором, который нужно удалить
     */
    public void deleteByEntity(Author author) {
        if(session.contains(author)) {
            session.remove(author);
        }
    }
}
