package vn.edu.fu.veazy.core.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fu.veazy.core.model.ReportModel;

@Repository
public class HibernateReportDao implements GenericDao<ReportModel, String> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ReportModel report) throws Exception {
        sessionFactory.getCurrentSession().save(report);
    }

    @Override
    public ReportModel findById(String id) throws Exception {
    	return sessionFactory.getCurrentSession().get(ReportModel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReportModel> findByExample(ReportModel exampleInstance) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(ReportModel.class)
                                                     .add(Example.create(exampleInstance))
                                                     .list();
        } catch (HibernateException e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReportModel> findByExample(ReportModel exampleInstance, int offset, int limit) throws Exception {
        try {
            return sessionFactory.getCurrentSession().createCriteria(ReportModel.class)
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
    public List<ReportModel> getAll() throws Exception {
        return sessionFactory.getCurrentSession().createCriteria(ReportModel.class).list();
    }

    @Override
    public void update(ReportModel report) throws Exception {
    	sessionFactory.getCurrentSession().update(report);
    }

    @Override
    public void delete(ReportModel report) throws Exception {
    	//TODO set flag?
    	sessionFactory.getCurrentSession().delete(report);
    }

    @Override
    public Long getCount() throws Exception {
    	return (Long) sessionFactory.getCurrentSession()
    			.createCriteria(ReportModel.class)
    			.setProjection(Projections.rowCount())
    			.uniqueResult();
    }

	
}
