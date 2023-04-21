package MiniDFlow.repository;

import MiniDFlow.entity.Document;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;



@Repository
public class DocumentRepository{
    private static Logger logger = Logger.getLogger(DocumentRepository.class.getName());

    @Autowired
    private SessionFactory sessionFactory;


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Создание нового документа в бд
     *
     * @param document все поля должны быть заполнены !!!кроме ID!!!
     *
     */
    @Transactional
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
    @Transactional
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
    @Transactional
    public List<Document> getAll() {
        String hql = "FROM Document";
        TypedQuery<Document> query = getSession().createQuery(hql, Document.class);
        return query.getResultList();
    }
    @Transactional
    public List<Document> getAllExistDocuments(){
        return getSession().createQuery("from Document d where d.isExist = true", Document.class).getResultList();
    }
}
