package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.model.UserModel;

@Repository
public class HibernateUserDao implements GenericDao<UserModel, String> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void save(UserModel user) throws Exception {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @Transactional
    public UserModel findById(String id) throws Exception {
    	return sessionFactory.getCurrentSession().get(UserModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public List<UserModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(UserModel.class).list();
    }

    @Override
    @Transactional
    public void update(UserModel user) throws Exception {
    	sessionFactory.getCurrentSession().update(user);
    }

    @Override
    @Transactional
    public void delete(UserModel user) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    @Transactional
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(UserModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

}
