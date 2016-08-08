package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.model.AnswerModel;


public class HibernateAnswerDao implements GenericDao<AnswerModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(AnswerModel ans) throws Exception {
        sessionFactory.getCurrentSession().save(ans);
    }

    @Override
    public AnswerModel findById(Integer id) throws Exception {
    	return sessionFactory.getCurrentSession().get(AnswerModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerModel> findByExample(AnswerModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(AnswerModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AnswerModel> findByExample(AnswerModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(AnswerModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .setFirstResult(offset)
                                                     .setMaxResults(limit)
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<AnswerModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(AnswerModel.class).list();
    }

    @Override
    public void update(AnswerModel ans) throws Exception {
    	sessionFactory.getCurrentSession().update(ans);
    }

    @Override
    public void delete(AnswerModel ans) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(ans);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(AnswerModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

    @Override
    public Object executeSql(String sql, Class clazz) throws Exception {
        Session ss = sessionFactory.getCurrentSession();
        Query query = ss.createSQLQuery(sql);
        if (clazz != null) {
            ((SQLQuery) query).addEntity(clazz);
        }
        return query.list();
    }

	
}
