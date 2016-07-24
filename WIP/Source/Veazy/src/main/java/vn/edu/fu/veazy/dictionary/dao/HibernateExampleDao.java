package vn.edu.fu.veazy.dictionary.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.dictionary.model.ExampleModel;

@Repository
public class HibernateExampleDao implements GenericDao<ExampleModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ExampleModel example) throws Exception {
        sessionFactory.getCurrentSession().save(example);
    }

    @Override
    public ExampleModel findById(Integer id) throws Exception {
    	return sessionFactory.getCurrentSession().get(ExampleModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ExampleModel> findByExample(ExampleModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(ExampleModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ExampleModel> findByExample(ExampleModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(ExampleModel.class)
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
    public List<ExampleModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(ExampleModel.class).list();
    }

    @Override
    public void update(ExampleModel example) throws Exception {
    	sessionFactory.getCurrentSession().update(example);
    }

    @Override
    public void delete(ExampleModel example) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(example);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(ExampleModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

	
}
