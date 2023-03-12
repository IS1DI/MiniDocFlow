package MiniDFlow.repository;

import MiniDFlow.repository.entity.Document;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentController implements DAOController<Document,Integer>{

    @Override
    public List<Document> getAll() {
        return null;
    }

    @Override
    public Document update(Document entity) {
        return null;
    }

    @Override
    public Document getById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean create(Document entity) {
        return false;
    }
}
