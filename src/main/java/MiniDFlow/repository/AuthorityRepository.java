package MiniDFlow.repository;

import MiniDFlow.entity.Authority;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class AuthorityRepository {
    @Autowired
    private Session session;
    public void update(Authority authority){
        Query<Authority> q = session.createQuery("from Authority where author = :aid and name = :name", Authority.class);
        q.setParameter("aid",authority.getAuthor());
        q.setParameter("name",authority.getName());
        try{
            q.getSingleResult();
        }catch (NoResultException ex){
            create(authority);
        }
    }

    public void create(Authority authority){
        session.persist(authority);
    }


    public List<Authority> findAuthoritiesByUsername(String username) {
        Query<Authority> q = session.createQuery("select au.authorities from Author au where au.username = :username", Authority.class);
        q.setParameter("username", username);
        return q.getResultList();
    }
}
