package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.model.QuestionModel;

public class HibernateQuestionDao implements GenericDao<QuestionModel, String> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(QuestionModel question) throws Exception {
        sessionFactory.getCurrentSession().save(question);
    }

    @Override
    public QuestionModel findById(String id) throws Exception {
    	return sessionFactory.getCurrentSession().get(QuestionModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionModel> findByExample(QuestionModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(QuestionModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QuestionModel> findByExample(QuestionModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(QuestionModel.class)
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
    public List<QuestionModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(QuestionModel.class).list();
    }

    @Override
    public void update(QuestionModel question) throws Exception {
    	sessionFactory.getCurrentSession().update(question);
    }

    @Override
    public void delete(QuestionModel question) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(question);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(QuestionModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

}