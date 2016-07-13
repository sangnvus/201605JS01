package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.model.UserModel;

public class HibernateUserDao implements GenericDao<UserModel, String> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(UserModel user) throws Exception {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public UserModel findById(String id) throws Exception {
    	return sessionFactory.getCurrentSession().get(UserModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserModel> findByExample(UserModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(UserModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserModel> findByExample(UserModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(UserModel.class)
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
    public List<UserModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(UserModel.class).list();
    }

    @Override
    public void update(UserModel user) throws Exception {
        // TODO Auto-generated method stub
    	sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(UserModel user) throws Exception {
    	sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(UserModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

}
