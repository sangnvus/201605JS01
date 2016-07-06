package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.model.UserModel;

public class HibernateUserDao implements GenericDao<UserModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(UserModel user) throws Exception {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public UserModel findById(Integer id) throws Exception {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public List<UserModel> findAll() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(UserModel user) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(UserModel user) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Long count() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
