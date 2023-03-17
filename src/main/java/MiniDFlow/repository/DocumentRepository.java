package MiniDFlow.repository;

import MiniDFlow.repository.entity.Author;
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
     * @param documentName название документа
     * @param author       автор документа
     */
    public void create(String documentName, Author author) {
        Document document = new Document(documentName, author);
        session.persist(document);
    }

    /**
     * Получение информации о документе по id
     *
     * @param id: идентификатор документа
     * @return объект Document, соответствующий заданному идентификатору ИЛИ null, если по такому id нет сущности
     * Если такого id не существует, то выбрасывается ошибка "документ не найден"
     */
    public Document getById(Integer id) throws NoSuchElementException {
        try {
            return session.find(Document.class, id);
        } catch (Exception ex) {
            throw new NoSuchElementException("Document with id " + id + " not found");
        }
    }

    public List<Document> getAll() {
        String hql = "FROM Document";
        TypedQuery<Document> query = session.createQuery(hql, Document.class);
        return query.getResultList();
    }

}
