package MiniDFlow.repository;

import MiniDFlow.repository.entity.Document;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;


@Repository
public class DocumentRepository{
    private Session session;

    DocumentRepository(Session session){
        this.session = session;
    }

    /**
     * Сохраняет документ в бд
     * @param document документ с заполненными полями !!!КРОМЕ ID!!!
     * */
    public void create(Document document){

    }

    public Document getById(Integer id){
        return null;
    }

}
