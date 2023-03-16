package MiniDFlow.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class DocumentVersionRepository{

    private Session session;

    DocumentVersionRepository(Session session){
        this.session = session;
    }
}
