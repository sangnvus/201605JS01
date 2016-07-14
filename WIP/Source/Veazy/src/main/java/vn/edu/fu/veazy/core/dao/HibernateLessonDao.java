package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.model.LessonModel;
import vn.edu.fu.veazy.core.model.LessonVersionModel;

public class HibernateLessonDao implements GenericDao<LessonModel, String> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(LessonModel lesson) throws Exception {
        sessionFactory.getCurrentSession().save(lesson);
    }

    @Override
    public LessonModel findById(String id) throws Exception {
    	return sessionFactory.getCurrentSession().get(LessonModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LessonModel> findByExample(LessonModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(LessonModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LessonModel> findByExample(LessonModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(LessonModel.class)
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
    public List<LessonModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(LessonModel.class).list();
    }

    @Override
    public void update(LessonModel lesson) throws Exception {
    	sessionFactory.getCurrentSession().update(lesson);
    }

    @Override
    public void delete(LessonModel lesson) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(lesson);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(LessonModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

	public List<LessonModel> getLessonOfCourse(String courseId) throws Exception {
		LessonModel sample = new LessonModel();
		sample.setCourseId(courseId);
		return findByExample(sample);
	}

}
