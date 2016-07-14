package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.form.CreateLessonForm;
import vn.edu.fu.veazy.core.form.UpdateLessonForm;
import vn.edu.fu.veazy.core.response.CreateLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonVersionResponse;
import vn.edu.fu.veazy.core.response.LessonOfCourseResponse;

public interface LessonService {
	CreateLessonResponse createLesson(String creatorId,CreateLessonForm form) throws Exception;
	GetLessonVersionResponse getLessonVersion(String lessonId,Integer version) throws Exception;
	void updateLesson(String requesterId,UpdateLessonForm form) throws Exception;
	void publishLessonVersion(String requesterId,String lessionId) throws Exception;
	void reportLesson(String reporterId,String lessonId,String content) throws Exception;
	List<LessonOfCourseResponse> getLessonsOfCourse(String courseId) throws Exception;
	GetLessonResponse getLesson(String lessonId)throws Exception;
}
