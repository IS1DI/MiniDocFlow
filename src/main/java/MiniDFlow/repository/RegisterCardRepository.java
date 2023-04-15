package MiniDFlow.repository;

import MiniDFlow.entity.Document;
import MiniDFlow.entity.RegisterCard;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;


@Repository
public class RegisterCardRepository{

    private Session session;
    RegisterCardRepository(Session session){
        this.session = session;
    }

    public void create(RegisterCard registerCard){
        session.persist(registerCard);
    }

    public void update(Document document, String documentExternNumber){
        Query q =  session.createQuery("update RegisterCard set documentExternNumber = :dExNum, dateExtern = :dEx where documentId = :did");
        q.setParameter("dExNum",documentExternNumber);
        q.setParameter("did",document);
        q.setParameter("dEx", Instant.now());
        q.executeUpdate();
    }
}
