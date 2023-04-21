package MiniDFlow.repository;

import MiniDFlow.entity.Author;
import MiniDFlow.entity.Document;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import MiniDFlow.entity.projection.DocumentView;
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
    @Transactional
    public List<DocumentView> getAllDocViews(){
        return getSession().createQuery("""
                select new MiniDFlow.entity.projection.DocumentView(
                    d.id,
                    d.name,
                    d.author.username,
                    d.lastVersion,
                    d.isExist,
                    d.registerCard.dateIntro,
                    d.registerCard.documentIntroNumber
                )
                from Document d
                order by d.id
                """,DocumentView.class).getResultList();
    }
    @Transactional
    public List<DocumentView> getAllDocViewsByAuthor(Author author) {
        TypedQuery<DocumentView> q = getSession().createQuery("""
                select new MiniDFlow.entity.projection.DocumentView(
                    d.id,
                    d.name,
                    d.author.username,
                    d.lastVersion,
                    d.isExist,
                    d.registerCard.dateIntro,
                    d.registerCard.documentIntroNumber
                )
                from Document d
                where d.author = :aid
                order by d.id
                """,DocumentView.class);
        q.setParameter("aid",author);
        return q.getResultList();
    }
}
