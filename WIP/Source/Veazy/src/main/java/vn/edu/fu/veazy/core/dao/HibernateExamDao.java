package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.model.ExamModel;

public class HibernateExamDao implements GenericDao<ExamModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ExamModel exam) throws Exception {
        sessionFactory.getCurrentSession().save(exam);
    }

    @Override
    public ExamModel findById(Integer id) throws Exception {
    	return sessionFactory.getCurrentSession().get(ExamModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ExamModel> findByExample(ExamModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(ExamModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ExamModel> findByExample(ExamModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(ExamModel.class)
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
    public List<ExamModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(ExamModel.class).list();
    }

    @Override
    public void update(ExamModel exam) throws Exception {
    	sessionFactory.getCurrentSession().update(exam);
    }

    @Override
    public void delete(ExamModel exam) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(exam);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(ExamModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

    @Override
    public Object executeSql(String sql, Class clazz) throws Exception {
        // TODO Auto-generated method stub
        return null;
        
    }

}
