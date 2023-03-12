package MiniDFlow.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Bean(name = "session")
    @Scope("prototype")
    public Session getSession(@Autowired SessionFactory sessionFactory){
        return sessionFactory.openSession();
    }
}
