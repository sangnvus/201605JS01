package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fu.veazy.core.model.TaskModel;

@Repository
public class HibernateTaskDao implements GenericDao<TaskModel, String> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(TaskModel task) throws Exception {
        sessionFactory.getCurrentSession().save(task);
    }

    @Override
    public TaskModel findById(String id) throws Exception {
    	return sessionFactory.getCurrentSession().get(TaskModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TaskModel> findByExample(TaskModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(TaskModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TaskModel> findByExample(TaskModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(TaskModel.class)
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
    public List<TaskModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(TaskModel.class).list();
    }

    @Override
    public void update(TaskModel task) throws Exception {
    	sessionFactory.getCurrentSession().update(task);
    }

    @Override
    public void delete(TaskModel task) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(task);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(TaskModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

	
}
