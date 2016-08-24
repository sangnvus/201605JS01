package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fu.veazy.core.model.LessonModel;

@Repository
public class HibernateLessonDao implements GenericDao<LessonModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(LessonModel lesson) throws Exception {
        sessionFactory.getCurrentSession().save(lesson);
    }

    @Override
    public LessonModel findById(Integer id) throws Exception {
    	return sessionFactory.getCurrentSession().get(LessonModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LessonModel> findByExample(LessonModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(LessonModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .addOrder(Order.asc("id"))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LessonModel> findByExample(LessonModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(LessonModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .addOrder(Order.asc("id"))
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
    public List<LessonModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(LessonModel.class).list();
    }

    @Override
    public void update(LessonModel lesson) throws Exception {
    	sessionFactory.getCurrentSession().update(lesson);
    }

    @Override
    public void delete(LessonModel lesson) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(lesson);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(LessonModel.class)
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
