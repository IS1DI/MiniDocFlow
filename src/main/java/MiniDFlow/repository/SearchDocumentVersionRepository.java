package MiniDFlow.repository;

import MiniDFlow.entity.DocumentVersion;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SearchDocumentVersionRepository {
    @Autowired
    SessionFactory sessionFactory;

    private FullTextSession getFullTextSession(){
        return Search.getFullTextSession( sessionFactory.getCurrentSession());
    }
    @Transactional
    public List<DocumentVersion> searchByDocumentName(String query, int limit) throws ParseException {
        FullTextSession session = getFullTextSession();
        QueryBuilder qb = session.getSearchFactory().buildQueryBuilder().forEntity(DocumentVersion.class).get();
        Query q = qb.keyword().onFields("documentId.name","content")
                .matching(query).createQuery();
        org.hibernate.Query<DocumentVersion> hq = session.createFullTextQuery(q, DocumentVersion.class);
        return hq.getResultList();
    }
}
