package MiniDFlow.repository;

import MiniDFlow.entity.Document;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;


@Repository
public class DocumentRepository{
    @Autowired
    private SessionFactory sessionFactory;


    private Session getSession() {
        return sessionFactory.openSession();
    }

    /**
     * Создание нового документа в бд
     *
     * @param document все поля должны быть заполнены !!!кроме ID!!!
     *
     */
    public void create(Document document) {
        getSession().save(document);
    }

    /**
     * Получение информации о документе по id
     *
     * @param id: идентификатор документа
     * @return объект Document, соответствующий заданному идентификатору ИЛИ null, если по такому id нет сущности
     * @throws NoSuchElementException если такого id не существует
     */
    public Document getById(Integer id) throws NoSuchElementException {
        try {
            return getSession().find(Document.class, id);
        } catch (Exception ex) {
            throw new NoSuchElementException("Document with id " + id + " not found");
        }
    }
    /**
     * Возвращает список всех документов
     * */
    public List<Document> getAll() {
        String hql = "FROM Document";
        TypedQuery<Document> query = getSession().createQuery(hql, Document.class);
        return query.getResultList();
    }

}
