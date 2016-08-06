package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateLuceneDao<T> implements LuceneDao<T> {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private FullTextSession ftSession;
    
    private Class<?> clazz;
    
    public HibernateLuceneDao() {
        super();
    }
    
    public HibernateLuceneDao(Class<?> clazz) {
        super();
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> luceneModel(String field, String term) throws ClassNotFoundException {
        Session ss = sessionFactory.getCurrentSession();
        ftSession = Search.getFullTextSession(ss);
        return (List<T>) ftSession.createFullTextQuery(
                new TermQuery(new Term(field, term)), Class.forName(clazz.getName()))
                .list();
    }

}
