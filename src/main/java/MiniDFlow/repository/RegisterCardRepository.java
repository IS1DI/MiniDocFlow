package MiniDFlow.repository;

import MiniDFlow.entity.Document;
import MiniDFlow.entity.RegisterCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;


@Repository
public class RegisterCardRepository{

    @Autowired
    private SessionFactory sessionFactory;
    private Session getSession() {
        return sessionFactory.openSession();
    }

    public void create(RegisterCard registerCard){
        getSession().save(registerCard);
    }

    public void update(Document document, String documentExternNumber){
        Query q =  getSession().createQuery("update RegisterCard set documentExternNumber = :dExNum, dateExtern = :dEx where documentId = :did");
        q.setParameter("dExNum",documentExternNumber);
        q.setParameter("did",document);
        q.setParameter("dEx", Instant.now());
        q.executeUpdate();
    }
}
