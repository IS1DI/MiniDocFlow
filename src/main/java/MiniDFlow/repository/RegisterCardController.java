package MiniDFlow.repository;

import MiniDFlow.repository.entity.RegisterCard;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterCardController implements DAOController<RegisterCard,Integer>{
    @Autowired
    private Session session;


    @Override
    public List<RegisterCard> getAll() {
        return null;
    }

    @Override
    public RegisterCard update(RegisterCard entity) {
        return null;
    }

    @Override
    public RegisterCard getById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean create(RegisterCard entity) {
        return false;
    }
}
