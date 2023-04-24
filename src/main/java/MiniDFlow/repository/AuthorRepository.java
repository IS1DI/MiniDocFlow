package MiniDFlow.repository;

import MiniDFlow.entity.Author;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class AuthorRepository {

    private static Logger logger = Logger.getLogger(AuthorRepository.class.getName());

    @Autowired
    private SessionFactory sessionFactory;


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Создание нового пользователя
     *
     * @param author Должны быть заполнены все поля
     *               !!!КРОМЕ ID!!!
     */
    @Transactional
    public void create(Author author){
            getSession().persist(author);
    }

    /**
     * Получение информации о пользователе по id
     *
     * @param id: идентификатор пользователя
     * @return объект Author, соответствующий заданному идентификатору ИЛИ null, если по такому id нет сущности
     */
    @Transactional
    public Author getById(Integer id) {
        return getSession().find(Author.class, id);
    }

    /**
     * Получение информации о пользователе по userName
     *
     * @param username: имя пользователя
     * @return объект Author, соответствующий заданному имени пользователя
     */
    @Transactional
    public Optional<Author> getByUsername(String username) {
        String hql = "FROM Author WHERE username = :u";
        Query<Author> query = getSession().createQuery(hql, Author.class);
        query.setParameter("u", username);
        List<Author> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }

    @Transactional
    public boolean exist(String username){
        Query q = getSession().createQuery("from Author where username = :username");
        q.setParameter("username",username);
        try{
            q.getSingleResult();
        }catch (NoResultException ex){
            return false;
        }
        return true;
    }

    /**
     * Удаление пользователя из базы по entity (в entity уже содержится id)
     *
     * @param author: объект Author с заданным идентификатором, который нужно удалить
     */
    @Transactional
    public void deleteByEntity(Author author) {
        if(getSession().contains(author)) {
            getSession().remove(author);
        }
    }
}
