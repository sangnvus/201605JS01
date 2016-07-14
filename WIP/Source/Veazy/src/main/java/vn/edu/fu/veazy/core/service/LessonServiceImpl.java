package vn.edu.fu.veazy.core.service;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.dao.HibernateLessonDao;
import vn.edu.fu.veazy.core.dao.HibernateLessonVersionDao;
import vn.edu.fu.veazy.core.dao.HibernateReportDao;
import vn.edu.fu.veazy.core.dao.HibernateTaskDao;
import vn.edu.fu.veazy.core.form.CreateLessonForm;
import vn.edu.fu.veazy.core.form.UpdateLessonForm;
import vn.edu.fu.veazy.core.model.LessonModel;
import vn.edu.fu.veazy.core.model.LessonVersionModel;
import vn.edu.fu.veazy.core.model.ReportModel;
import vn.edu.fu.veazy.core.model.TaskModel;
import vn.edu.fu.veazy.core.response.CreateLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonVersionResponse;
import vn.edu.fu.veazy.core.response.LessonOfCourseResponse;

public class LessonServiceImpl implements LessonService{
	@Autowired
	private HibernateLessonDao lessonDao;
	@Autowired
	private HibernateLessonVersionDao lessonVersionDao;
	@Autowired
	private HibernateReportDao reportDao;
	@Autowired
	private HibernateTaskDao taskDao;
	
	@Override
	public CreateLessonResponse createLesson(String creatorId, CreateLessonForm form) throws Exception {
		// get data from form
		LessonModel lesson = new LessonModel();
		lesson.setCourseId(form.getCourseId());
		List<LessonModel> listLesson = lessonDao.getLessonOfCourse(form.getCourseId());
		if(listLesson == null || listLesson.isEmpty()){
			lesson.setIndex(Const.START_INDEX);
		}
		else{
			lesson.setIndex(listLesson.size()+1);
		}
		LessonVersionModel lessonVersion = new LessonVersionModel();
		lessonVersion.setCreatorId(creatorId);
		lessonVersion.setDescription(form.getDescription());
		lessonVersion.setTitle(form.getLessonTitle());
		lessonVersion.setVersion(Const.START_INDEX);
		
		lessonVersion.setArticle(form.getArticle());
		lessonVersion.setGrammar(form.getGrammar());
		lessonVersion.setListening(form.getListening());
		lessonVersion.setPractice(form.getPractice());
		lessonVersion.setReading(form.getReading());
		lessonVersion.setVocabulary(form.getVocabulary());
		
		//set date time
		lesson.setCreateDate(System.currentTimeMillis());
		lessonVersion.setCreateDate(System.currentTimeMillis());
		
		lessonVersion.setState(Const.UPDATING);
		
		//save to db
		lessonDao.save(lesson);
		lessonVersionDao.save(lessonVersion);
		
		lesson.setCurrentVersionId(lessonVersion.getId());
		lessonVersion.setLessonId(lesson.getId());
		
		lessonDao.update(lesson);
		lessonVersionDao.update(lessonVersion);
		
		return new CreateLessonResponse(lesson,lessonVersion);
		
		
	}

	@Override
	public GetLessonVersionResponse getLessonVersion(String lessonId, Integer version) throws Exception {
		//get lesson
		LessonModel lesson =  lessonDao.findById(lessonId);
		if(lesson == null){
			throw new Exception("lesson doesn't exist");
		}
		//get all version in lesson
		
		List<LessonVersionModel> listVersion = lessonVersionDao.getVersionOfLesson(lessonId);
				
		//get request version
		LessonVersionModel lessonVersion = null;
		for (LessonVersionModel versionModel : listVersion) {
			if(versionModel.getVersion() == version){
				lessonVersion = versionModel;
				break;
			}
		}
		if(lessonVersion == null){
			throw new Exception("lesson version doesn't exist");
		}
		
		// get list version index
		List<Integer> listIndex = new Vector<Integer>();
		for (LessonVersionModel versionModel : listVersion) {
			listIndex.add(versionModel.getVersion());
		}
		//return
		return new GetLessonVersionResponse(lesson,lessonVersion,listIndex);
	}

	@Override
	public void updateLesson(String requesterId, UpdateLessonForm form) throws Exception {
		//get lesson
		LessonModel lesson = lessonDao.findById(form.getLessonId());
		if(lesson == null){
			throw new Exception("lesson doesn't exist");
		}
		
		//get version in updating state
		LessonVersionModel sample = new LessonVersionModel();
		sample.setLessonId(form.getLessonId());
		sample.setState(Const.UPDATING);
		List<LessonVersionModel> listUpdating=  lessonVersionDao.findByExample(sample);
		LessonVersionModel updatingVersion = null;
		
		//check if have updating version or not
		if(listUpdating != null && listUpdating.size()>0){
			updatingVersion = listUpdating.get(0);
			//check if requester is creator
			if(updatingVersion.getCreatorId() != requesterId){
				throw new Exception("requester isn't creator");
			}
			updatingVersion.setUpdateDate(System.currentTimeMillis());
		}else{
			updatingVersion = new LessonVersionModel();
			updatingVersion.setCreatorId(requesterId);
			updatingVersion.setCreateDate(System.currentTimeMillis());
			updatingVersion.setVersion(lessonVersionDao.getVersionOfLesson(form.getLessonId()).size()+1);
			updatingVersion.setLessonId(form.getCourseId());
			updatingVersion.setState(Const.UPDATING);
		}
		
		updatingVersion.setArticle(form.getArticle());
		updatingVersion.setGrammar(form.getGrammar());
		updatingVersion.setListening(form.getListening());
		updatingVersion.setPractice(form.getPractice());
		updatingVersion.setReading(form.getReading());
		updatingVersion.setVocabulary(form.getVocabulary());
		
		updatingVersion.setDescription(form.getDescription());
		updatingVersion.setTitle(form.getLessonTitle());
		
		//save to db
		lessonVersionDao.save(updatingVersion);
	}

	@Override
	public void publishLessonVersion(String requesterId, String lessionId) throws Exception {
		LessonVersionModel sample = new LessonVersionModel();
		sample.setLessonId(lessionId);
		sample.setState(Const.UPDATING);
		List<LessonVersionModel> listUpdating=  lessonVersionDao.findByExample(sample);
		LessonVersionModel updatingVersion = null;
		
		//check if have updating version or not
		if(listUpdating != null && listUpdating.size()>0){
			updatingVersion = listUpdating.get(0);
			//check if requester is creator
			if(updatingVersion.getCreatorId() != requesterId){
				throw new Exception("requester isn't creator");
			}
			updatingVersion.setState(Const.REVIEWING);
			lessonVersionDao.update(updatingVersion);
			//create task
			TaskModel task = new TaskModel();
			task.setSenderId(requesterId);
			task.setCreateDate(System.currentTimeMillis());
			task.setReadFlag(false);
			//TODO config task content
			task.setContent("");
			//save to db
			taskDao.save(task);
		}else{
			throw new Exception("don't have version can be published");
		}
	}

	@Override
	public void reportLesson(String reporterId, String lessonId, String content) throws Exception {
		ReportModel report = new ReportModel();
		report.setSenderId(reporterId);
		report.setContent(content);
		report.setLessonId(lessonId);
		report.setCreateDate(System.currentTimeMillis());
		//save to db
		reportDao.save(report);
		
	}

	@Override
	public List<LessonOfCourseResponse> getLessonsOfCourse(String courseId) throws Exception {
		LessonModel sample = new LessonModel();
		sample.setCourseId(courseId);
		List<LessonModel> listLesson = lessonDao.findByExample(sample);
		if(listLesson == null || listLesson.isEmpty()){
			return null;
		}
		List<LessonOfCourseResponse> listResult = new Vector<LessonOfCourseResponse>();
		for (LessonModel lessonModel : listLesson) {
			LessonOfCourseResponse response = new LessonOfCourseResponse();
			response.setLessonId(lessonModel.getId());
			//get title from current version
			LessonVersionModel version = lessonVersionDao.findById(lessonModel.getCurrentVersionId());
			response.setTitle(version.getTitle());
			listResult.add(response);
		}
		return listResult;
	}

	@Override
	public GetLessonResponse getLesson(String lessonId) throws Exception {
		LessonModel lesson =  lessonDao.findById(lessonId);
		if(lesson == null){
			throw new Exception("lesson doesn't exist");
		}
		LessonVersionModel currentVersion = lessonVersionDao.findById(lesson.getCurrentVersionId());
		//get previous,next lesson id
		String nextId = null,previousId = null;
		if(lesson.getIndex() != Const.START_INDEX){
			LessonModel sample = new LessonModel();
			sample.setCourseId(lesson.getCourseId());
			sample.setIndex(lesson.getIndex()-1);
			previousId = lessonDao.findByExample(sample).get(0).getId();
		}
		LessonModel sample = new LessonModel();
		sample.setCourseId(lesson.getCourseId());
		sample.setIndex(lesson.getIndex()+1);
		List<LessonModel> listLesson = lessonDao.findByExample(sample);
		if(listLesson != null && !listLesson.isEmpty()){
			nextId = listLesson.get(0).getId();
		}
		
		return new GetLessonResponse(lesson,currentVersion,nextId,previousId);
	}

}
