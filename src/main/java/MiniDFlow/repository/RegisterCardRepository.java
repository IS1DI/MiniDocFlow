package MiniDFlow.repository;

import MiniDFlow.entity.Document;
import MiniDFlow.entity.RegisterCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.logging.Logger;


@Repository
public class RegisterCardRepository{

    private static Logger logger = Logger.getLogger(RegisterCardRepository.class.getName());

    @Autowired
    private SessionFactory sessionFactory;
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void create(RegisterCard registerCard){
        getSession().save(registerCard);
    }
    @Transactional
    public void update(Document id, String documentExternNumber){
        Query<RegisterCard> q = getSession().createQuery("from RegisterCard where documentId= :did",RegisterCard.class);
        q.setParameter("did",id);
        RegisterCard registerCard = q.getSingleResult();
        registerCard.setDateExtern(Instant.now());
        registerCard.setDocumentExternNumber(documentExternNumber);
        getSession().merge(registerCard);
    }

}
