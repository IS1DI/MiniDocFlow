package MiniDFlow.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DAOController<E,ID> {

    List<E> getAll();
    E update(E entity);
    E getById(ID id);
    boolean delete(ID id);
    boolean create(E entity);

}
