package MiniDFlow.repository;

import MiniDFlow.entity.Author;
import MiniDFlow.entity.Document;
import MiniDFlow.entity.DocumentVersion;
import javax.persistence.NoResultException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DocumentVersionRepository{

    private Session session;

    public DocumentVersionRepository(Session session){
        this.session = session;
    }

    /**
     * CREATE
     * @param documentVersion
     */
    public void create(DocumentVersion documentVersion){
        session.persist(documentVersion);
    }


    /**
     * READ
     * @param document
     */
    public List<DocumentVersion> getAllVerByDocument(Document document){
        Query<DocumentVersion> q = session.createQuery("from DocumentVersion where documentId = :did",DocumentVersion.class);
        q.setParameter("did",document);
        return q.getResultList();
    }


    /**
     * READ
     * @param author
     */
    public List<DocumentVersion> getAllVerByAuthor(Author author){
        Query<DocumentVersion> q = session.createQuery("from DocumentVersion where versionAuthor = :aid", DocumentVersion.class);
        q.setParameter("aid",author);
        return q.getResultList();
    }

    /**
     * READ
     * @param document - документ по которому ищется версия
     * @return DocumentVersion
     * @throws NoResultException - не найдено ни одного результата
     */
    public DocumentVersion getLastVerByDocument(Document document) throws NoResultException{
        Query<DocumentVersion> q = session.createQuery("from DocumentVersion where documentId = :did order by version desc limit 1", DocumentVersion.class);
        q.setParameter("did",document);
        return q.getSingleResult();
    }

    /**
     * READ
     * @param author - автор по которому ищется версия
     * @return DocumentVersion
     * @throws NoResultException - не найдено ни одного результата
     */
    public DocumentVersion getLastVerByAuthor(Author author) throws NoResultException {
        Query<DocumentVersion> q = session.createQuery("from DocumentVersion where versionAuthor = :aid order by version desc limit 1", DocumentVersion.class);
        q.setParameter("aid", author);
        return q.getSingleResult();
    }

    /**
     * UPDATE
     * @param documentVersion -
     */
    public void update(DocumentVersion documentVersion){
        Query<Integer> q = session.createQuery("select max(version) from DocumentVersion where documentId = :did", Integer.class );
        q.setParameter("did",documentVersion.getDocumentId());
        documentVersion.setVersion(q.getSingleResult() + 1);
        session.persist(documentVersion);
    }
}
