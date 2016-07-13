package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.model.CourseModel;

public class HibernateCourseDao implements GenericDao<CourseModel, Integer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(CourseModel course) throws Exception {
        sessionFactory.getCurrentSession().save(course);
    }

    @Override
    public CourseModel findById(Integer id) throws Exception {
    	return sessionFactory.getCurrentSession().get(CourseModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CourseModel> findByExample(CourseModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(CourseModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CourseModel> findByExample(CourseModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(CourseModel.class)
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
    public List<CourseModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(CourseModel.class).list();
    }

    @Override
    public void update(CourseModel course) throws Exception {
        // TODO Auto-generated method stub
    	sessionFactory.getCurrentSession().update(course);
    }

    @Override
    public void delete(CourseModel course) throws Exception {
    	sessionFactory.getCurrentSession().delete(course);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(CourseModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

}
