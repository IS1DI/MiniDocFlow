package MiniDFlow.service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class SessionFactoryService {

    @Bean(name = "sessionFactory")
    @Scope("singleton")
    public SessionFactory getSessionFactory(){
        try {
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                    .configure().build();
            Metadata metadata = new MetadataSources(ssr)
                    .getMetadataBuilder()
                    .build();
            return metadata
                    .getSessionFactoryBuilder()
                    .build();
        }catch (Throwable thr){
            throw new ExceptionInInitializerError(thr);
        }
    }
}
