package MiniDFlow.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class DocumentRepository{
    private Session session;

    DocumentRepository(Session session){
        this.session = session;
    }

}
