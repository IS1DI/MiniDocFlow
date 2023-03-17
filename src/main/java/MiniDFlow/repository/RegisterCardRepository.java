package MiniDFlow.repository;

import MiniDFlow.repository.entity.RegisterCard;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class RegisterCardRepository{

    private Session session;
    RegisterCardRepository(Session session){
        this.session = session;
    }

    public void create(RegisterCard registerCard){

    }

}
