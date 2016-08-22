package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.model.UserModel;

@Repository
public class HibernateUserDao implements GenericDao<UserModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(UserModel user) throws Exception {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public UserModel findById(Integer id) throws Exception {
    	return sessionFactory.getCurrentSession().get(UserModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserModel> findByExample(UserModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(UserModel.class)
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
    public List<UserModel> findByExample(UserModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(UserModel.class)
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
    public List<UserModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(UserModel.class)
                .addOrder(Order.asc("id")).list();
    }

    @Override
    public void update(UserModel user) throws Exception {
    	sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(UserModel user) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(UserModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

    @Override
    public Object executeSql(String sql, Class clazz) throws Exception {
        // TODO Auto-generated method stub
        return null;
        
    }

}
