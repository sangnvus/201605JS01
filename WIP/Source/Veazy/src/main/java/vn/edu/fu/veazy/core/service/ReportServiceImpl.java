package vn.edu.fu.veazy.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.form.ReportForm;
import vn.edu.fu.veazy.core.model.ReportModel;
import vn.edu.fu.veazy.core.model.UserModel;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	private GenericDao<ReportModel, Integer> reportDao;

    @Override
    @Transactional
    public void saveReport(ReportModel report) throws Exception {
        try {
            reportDao.save(report);
        } catch (Exception e) {
            // TODO custom exception
            throw new Exception(e.getMessage(), e);
        }
    }

	@Override
	@Transactional
	public ReportModel getReport(Integer id) throws Exception {
		return reportDao.findById(id);	
	}
	
	@Override
	@Transactional
	public List<ReportModel> getAllReports(Integer receiverId) throws Exception {
		ReportModel sample = new ReportModel();
		sample.setReceiverId(receiverId);
		sample.setDeleteFlag(false);
		return reportDao.findByExample(sample);
	}

	@Override
	@Transactional
	public void readReport(Integer id) throws Exception {
		ReportModel report = reportDao.findById(id);
		report.setReadFlag(true);
		reportDao.update(report);
	}

	@Override
	@Transactional
	public void deleteReport(Integer id) throws Exception {
		ReportModel report = reportDao.findById(id);
		report.setDeleteFlag(true);
		report.setDeleteDate(System.currentTimeMillis());
		reportDao.update(report);
	}
}
