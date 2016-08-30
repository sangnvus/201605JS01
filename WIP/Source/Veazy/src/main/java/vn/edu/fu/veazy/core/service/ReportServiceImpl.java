package vn.edu.fu.veazy.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.LessonModel;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.model.ReportModel;
import vn.edu.fu.veazy.core.model.UserModel;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	private GenericDao<ReportModel, Integer> reportDao;
	
	@Autowired
    private GenericDao<QuestionModel, Integer> questionDao;
	
	@Autowired
	private GenericDao<LessonModel, Integer> lessonDao;
	
	@Autowired
    private GenericDao<UserModel, Integer> userDao;
	
	

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
	public void deleteReport(Integer userId, Integer reportId) throws Exception {
		ReportModel report = reportDao.findById(reportId);
		if(report.getReceiverId() != userId){
			throw new Exception("user not have authen");
		}
			
		report.setDeleteFlag(true);
		report.setDeleteDate(System.currentTimeMillis());
		reportDao.update(report);
	}
	
	@Override
	@Transactional
	public void reportLesson(Integer reporterId, Integer lessonId, String content) throws Exception {
		LessonModel lesson = lessonDao.findById(lessonId);
		if(lesson == null || lesson.isDeleteFlag()){
			throw new Exception("lesson doesn't exist");
		}
		UserModel sample = new UserModel();
		sample.setRole(Const.ROLE_EDITOR);
		sample.setIsBanned(false);
		List<UserModel> listReceiver = userDao.findByExample(sample);
        sample.setRole(Const.ROLE_ADMIN);
        List<UserModel> listExtended = userDao.findByExample(sample);
        if (listExtended != null && listExtended.size() > 0) {
            listReceiver.addAll(listExtended);
        }
		
		for (UserModel userModel : listReceiver) {
			ReportModel report = new ReportModel();
			report.setSenderId(reporterId);
			report.setReceiverId(userModel.getId());
			report.setContent(content);
			report.setLessonId(lessonId);
//			report.setQuestionId(-1);
			report.setCreateDate(System.currentTimeMillis());
            report.setReadFlag(false);
			//save to db
			reportDao.save(report);
		}
	}
	
	@Override
	@Transactional
	public void reportQuestion(Integer reporterId, Integer questionId, String content) throws Exception {
		QuestionModel question = questionDao.findById(questionId);
		if(question == null || question.isDeleteFlag()){
			throw new Exception("question doesn't exist");
		}
		UserModel sample = new UserModel();
		sample.setRole(Const.ROLE_EDITOR);
		sample.setIsBanned(false);
		List<UserModel> listReceiver = userDao.findByExample(sample);
        sample.setRole(Const.ROLE_ADMIN);
        List<UserModel> listExtended = userDao.findByExample(sample);
        if (listExtended != null && listExtended.size() > 0) {
            listReceiver.addAll(listExtended);
        }

		for (UserModel userModel : listReceiver) {
			ReportModel report = new ReportModel();
			report.setSenderId(reporterId);
			report.setReceiverId(userModel.getId());
			report.setContent(content);
			report.setQuestionId(questionId);
//			report.setLessonId(-1);
			report.setCreateDate(System.currentTimeMillis());
			report.setReadFlag(false);
			//save to db
			reportDao.save(report);
		}
	}
}
