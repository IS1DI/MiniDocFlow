package MiniDFlow.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {

    private Session session;

    AuthorRepository(Session session){
        this.session = session;
    }


}
