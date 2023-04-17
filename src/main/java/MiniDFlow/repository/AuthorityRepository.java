package MiniDFlow.repository;

import MiniDFlow.entity.Authority;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class AuthorityRepository {
    @Autowired
    private SessionFactory sessionFactory;


    public void update(Authority authority){
        Query<Authority> q = getSession().createQuery("from Authority where author = :aid and name = :name", Authority.class);
        q.setParameter("aid",authority.getAuthor());
        q.setParameter("name",authority.getName());
        try{
            q.getSingleResult();
        }catch (NoResultException ex){
            create(authority);
        }
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }

    public void create(Authority authority){
        getSession().save(authority);
    }


    public List<Authority> findAuthoritiesByUsername(String username) {
        Query<Authority> q = getSession().createQuery("select au.authorities from Author au where au.username = :username", Authority.class);
        q.setParameter("username", username);
        return q.getResultList();
    }
}
