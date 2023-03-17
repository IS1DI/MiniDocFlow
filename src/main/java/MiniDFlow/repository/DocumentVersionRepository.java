package MiniDFlow.repository;

import MiniDFlow.repository.entity.DocumentVersion;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class DocumentVersionRepository{

    private Session session;

    DocumentVersionRepository(Session session){
        this.session = session;
    }

    public void update(DocumentVersion documentVersion){
        Query q = session.createQuery("select max(version) from DocumentVersion where documentId = :did" );
        q.setParameter("did",documentVersion.getDocumentId());
        documentVersion.setVersion(q.getMaxResults() + 1);
        session.persist(documentVersion);
    }
}
