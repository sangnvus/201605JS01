package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.model.LessonVersionModel;

public class HibernateLessonVersionDao implements GenericDao<LessonVersionModel, String> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(LessonVersionModel lessonversion) throws Exception {
        sessionFactory.getCurrentSession().save(lessonversion);
    }

    @Override
    public LessonVersionModel findById(String id) throws Exception {
    	return sessionFactory.getCurrentSession().get(LessonVersionModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LessonVersionModel> findByExample(LessonVersionModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(LessonVersionModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LessonVersionModel> findByExample(LessonVersionModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(LessonVersionModel.class)
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
    public List<LessonVersionModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(LessonVersionModel.class).list();
    }

    @Override
    public void update(LessonVersionModel lessonversion) throws Exception {
        // TODO Auto-generated method stub
    	sessionFactory.getCurrentSession().update(lessonversion);
    }

    @Override
    public void delete(LessonVersionModel lessonversion) throws Exception {
    	sessionFactory.getCurrentSession().delete(lessonversion);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(LessonVersionModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

}
