package vn.edu.fu.veazy.dictionary.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.dictionary.model.VijaModel;

@Repository
public class HibernateVijaDao implements GenericDao<VijaModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(VijaModel vija) throws Exception {
        sessionFactory.getCurrentSession().save(vija);
    }

    @Override
    public VijaModel findById(Integer id) throws Exception {
    	return sessionFactory.getCurrentSession().get(VijaModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VijaModel> findByExample(VijaModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(VijaModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VijaModel> findByExample(VijaModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(VijaModel.class)
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
    public List<VijaModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(VijaModel.class).list();
    }

    @Override
    public void update(VijaModel vija) throws Exception {
    	sessionFactory.getCurrentSession().update(vija);
    }

    @Override
    public void delete(VijaModel vija) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(vija);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(VijaModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

	
}
