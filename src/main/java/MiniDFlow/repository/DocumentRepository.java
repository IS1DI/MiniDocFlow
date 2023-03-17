package MiniDFlow.repository;

import MiniDFlow.repository.entity.Document;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;


@Repository
public class DocumentRepository{
    private Session session;

    DocumentRepository(Session session){
        this.session = session;
    }

    /**
     * Создание нового документа в бд
     *
     * @param document все поля должны быть заполнены !!!кроме ID!!!
     *
     */
    public void create(Document document) {
        session.persist(document);
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
            return session.find(Document.class, id);
        } catch (Exception ex) {
            throw new NoSuchElementException("Document with id " + id + " not found");
        }
    }
    /**
     * Возвращает список всех документов
     * */
    public List<Document> getAll() {
        String hql = "FROM Document";
        TypedQuery<Document> query = session.createQuery(hql, Document.class);
        return query.getResultList();
    }

}
