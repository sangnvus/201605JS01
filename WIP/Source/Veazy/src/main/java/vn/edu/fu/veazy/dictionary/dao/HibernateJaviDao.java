package vn.edu.fu.veazy.dictionary.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.dictionary.model.JaviModel;

@Repository
public class HibernateJaviDao implements GenericDao<JaviModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(JaviModel javi) throws Exception {
        sessionFactory.getCurrentSession().save(javi);
    }

    @Override
    public JaviModel findById(Integer id) throws Exception {
    	return sessionFactory.getCurrentSession().get(JaviModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<JaviModel> findByExample(JaviModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(JaviModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<JaviModel> findByExample(JaviModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(JaviModel.class)
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
    public List<JaviModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(JaviModel.class).list();
    }

    @Override
    public void update(JaviModel javi) throws Exception {
    	sessionFactory.getCurrentSession().update(javi);
    }

    @Override
    public void delete(JaviModel javi) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(javi);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(JaviModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

	
}
