package MiniDFlow.repository;

import MiniDFlow.entity.Author;
import MiniDFlow.entity.Document;
import MiniDFlow.entity.DocumentVersion;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;


@Repository
public class DocumentVersionRepository{
    private static Logger logger = Logger.getLogger(DocumentVersionRepository.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * CREATE
     * @param documentVersion
     */
    @Transactional
    public void create(DocumentVersion documentVersion){
        getSession().save(documentVersion);
    }


    /**
     * READ
     * @param document
     */
    @Transactional
    public List<DocumentVersion> getAllVerByDocument(Document document){
        Query<DocumentVersion> q = getSession().createQuery("from DocumentVersion where documentId = :did",DocumentVersion.class);
        q.setParameter("did",document);
        return q.getResultList();
    }


    /**
     * READ
     * @param author
     */
    public List<DocumentVersion> getAllVerByAuthor(Author author){
        Query<DocumentVersion> q = getSession().createQuery("from DocumentVersion where versionAuthor = :aid", DocumentVersion.class);
        q.setParameter("aid",author);
        return q.getResultList();
    }
    /**
     * READ
     * @param author - автор по которому ищется версия
     * @return DocumentVersion
     * @throws NoResultException - не найдено ни одного результата
     */
    public DocumentVersion getLastVerByAuthor(Author author) throws NoResultException {
        Query<DocumentVersion> q = getSession().createQuery("from DocumentVersion where versionAuthor = :aid order by version desc limit 1", DocumentVersion.class);
        q.setParameter("aid", author);
        return q.getSingleResult();
    }

    /**
     * UPDATE
     * @param documentVersion -
     */
    @Transactional
    public void update(DocumentVersion documentVersion){
        getSession().save(documentVersion);
    }
    public List<DocumentVersion> getAllVersions(){
        Query<DocumentVersion> q = getSession().createQuery("from DocumentVersion order by documentId.id,version", DocumentVersion.class);
        return q.getResultList();
    }
    @Transactional
    public DocumentVersion getVersionByDocument(Document document, int version){
        Query<DocumentVersion> q = getSession().createQuery("from DocumentVersion where documentId = :did and version = :ver",DocumentVersion.class);
        q.setParameter("did",document);
        q.setParameter("ver",version);
        return q.getSingleResult();
    }
}
