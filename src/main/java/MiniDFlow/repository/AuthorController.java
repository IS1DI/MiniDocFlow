package MiniDFlow.repository;

import MiniDFlow.repository.entity.Author;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorController  implements DAOController<Author,Integer>{

    @Override
    public List<Author> getAll() {
        return null;
    }

    @Override
    public Author update(Author entity) {
        return null;
    }

    @Override
    public Author getById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public boolean create(Author entity) {
        return false;
    }
}
