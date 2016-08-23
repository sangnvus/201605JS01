package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.model.ExamQuestionModel;

public class HibernateExamQuestionDao implements GenericDao<ExamQuestionModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ExamQuestionModel question) throws Exception {
        sessionFactory.getCurrentSession().save(question);
    }

    @Override
    public ExamQuestionModel findById(Integer id) throws Exception {
    	return sessionFactory.getCurrentSession().get(ExamQuestionModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ExamQuestionModel> findByExample(ExamQuestionModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(ExamQuestionModel.class)
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
    public List<ExamQuestionModel> findByExample(ExamQuestionModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(ExamQuestionModel.class)
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
    public List<ExamQuestionModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(ExamQuestionModel.class)
                .addOrder(Order.asc("id")).list();
    }

    @Override
    public void update(ExamQuestionModel question) throws Exception {
    	sessionFactory.getCurrentSession().update(question);
    }

    @Override
    public void delete(ExamQuestionModel question) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(question);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(ExamQuestionModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

    @Override
    public Object executeSql(String sql, Class clazz) throws Exception {
        // TODO Auto-generated method stub
        return null;
        
    }

}