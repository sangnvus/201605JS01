package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.model.ReportModel;

public interface ReportService {
    void saveReport(ReportModel report) throws Exception;
	ReportModel getReport(Integer id) throws Exception;
	List<ReportModel> getAllReports(Integer receiverId) throws Exception;
	void readReport(Integer id) throws Exception;
	void deleteReport(Integer userId, Integer reportId) throws Exception;
	void reportLesson(Integer reporterId,Integer lessonId,String content) throws Exception;
	void reportQuestion(Integer reporterId,Integer questionId,String content) throws Exception;
}
