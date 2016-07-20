package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.form.CreateLessonForm;
import vn.edu.fu.veazy.core.form.UpdateLessonForm;
import vn.edu.fu.veazy.core.response.CreateLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonVersionResponse;
import vn.edu.fu.veazy.core.response.LessonOfCourseResponse;

public interface LessonService {
	CreateLessonResponse createLesson(Integer creatorId,CreateLessonForm form) throws Exception;
	GetLessonVersionResponse getLessonVersion(Integer lessonId,Integer version) throws Exception;
	void updateLesson(Integer requesterId,UpdateLessonForm form) throws Exception;
	void publishLessonVersion(Integer requesterId,Integer lessionId) throws Exception;
	void reportLesson(Integer reporterId,Integer lessonId,String content) throws Exception;
	List<LessonOfCourseResponse> getLessonsOfCourse(Integer courseId) throws Exception;
	GetLessonResponse getLesson(Integer lessonId)throws Exception;
	void approveLessonChange(Integer reviewerId,Integer lessonId) throws Exception;
	void declineLessonChange(Integer reviewerId,Integer lessonId, String comment) throws Exception;
}
