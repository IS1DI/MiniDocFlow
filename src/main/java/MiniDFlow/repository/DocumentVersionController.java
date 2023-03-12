package MiniDFlow.repository;

import MiniDFlow.repository.entity.DocumentVersion;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentVersionController implements DAOController<DocumentVersion,Integer>{

    @Autowired
    private Session session;

    @Override
    public List<DocumentVersion> getAll() {
        return null;
    }

    @Override
    public DocumentVersion update(DocumentVersion entity) {
        return null;
    }

    @Override
    public DocumentVersion getById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean create(DocumentVersion entity) {
        return false;
    }
}
