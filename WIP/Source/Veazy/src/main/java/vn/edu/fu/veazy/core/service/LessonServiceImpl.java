package vn.edu.fu.veazy.core.service;

import java.util.List;
import java.util.Vector;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.controller.LessonController;
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
@Service
public class LessonServiceImpl implements LessonService{
    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LessonServiceImpl.class);
	@Autowired
	private GenericDao<LessonModel, Integer> lessonDao;
	@Autowired
	private GenericDao<LessonVersionModel, Integer> lessonVersionDao;
	@Autowired
	private GenericDao<ReportModel, Integer> reportDao;
	@Autowired
	private GenericDao<TaskModel, Integer> taskDao;
	
	@Override
	@Transactional
	public CreateLessonResponse createLesson(Integer creatorId, CreateLessonForm form) throws Exception {
		// get data from form
		LessonModel lesson = new LessonModel();
		lesson.setCourseId(form.getCourseId());
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
		lessonVersion.setLessonId(lesson.getId());

        lessonVersionDao.save(lessonVersion);
        
        lesson.setCurrentVersionId(lessonVersion.getId());
		lessonDao.update(lesson);
		
		return new CreateLessonResponse(lesson,lessonVersion);
		
		
	}

	@Override
	@Transactional
	public GetLessonVersionResponse getLessonVersion(Integer lessonId, Integer version) throws Exception {
		//get lesson
		LessonModel lesson =  lessonDao.findById(Integer.valueOf(lessonId));
		if(lesson == null){
			throw new Exception("lesson doesn't exist");
		}
		//get all version in lesson
		
		List<LessonVersionModel> listVersion = getVersionOfLesson(lessonId);
				
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
	@Transactional
	public void updateLesson(Integer requesterId, UpdateLessonForm form) throws Exception {
		//get lesson
		LessonModel lesson = lessonDao.findById(Integer.valueOf(form.getLessonId()));
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
			updatingVersion.setVersion(getVersionOfLesson(form.getLessonId()).size()+1);
			updatingVersion.setLessonId(form.getLessonId());
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
	@Transactional
	public void publishLessonVersion(Integer requesterId, Integer lessionId) throws Exception {
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
	@Transactional
	public void reportLesson(Integer reporterId, Integer lessonId, String content) throws Exception {
		ReportModel report = new ReportModel();
		report.setSenderId(reporterId);
		report.setContent(content);
		report.setLessonId(lessonId);
		report.setCreateDate(System.currentTimeMillis());
		//save to db
		reportDao.save(report);
		
	}

	@Override
    @SuppressWarnings("unchecked")
	@Transactional
	public List<LessonOfCourseResponse> getLessonsOfCourse(Integer courseId) throws Exception {
		LessonModel sample = new LessonModel();
		sample.setCourseId(courseId);
		String sql = "select * from Lesson les where les.courseId = " + courseId;
        List<LessonModel> listLesson = (List<LessonModel>) lessonDao.executeSql(sql, LessonModel.class);
		if(listLesson == null || listLesson.isEmpty()){
		    LOGGER.error(listLesson + ": No lesson for " + sample.getCourseId());
			return null;
		}
		List<LessonOfCourseResponse> listResult = new Vector<LessonOfCourseResponse>();
		for (LessonModel lessonModel : listLesson) {
			LessonOfCourseResponse response = new LessonOfCourseResponse();
			response.setLessonId(String.valueOf(lessonModel.getId()));
			//get title from current version
			LessonVersionModel version = lessonVersionDao
			        .findById(Integer.valueOf(lessonModel.getCurrentVersionId()));
			response.setTitle(version.getTitle());
			listResult.add(response);
		}
		return listResult;
	}

	@Override
	@Transactional
	public GetLessonResponse getLesson(Integer lessonId) throws Exception {
		LessonModel lesson =  lessonDao.findById(Integer.valueOf(lessonId));
		if(lesson == null){
			throw new Exception("lesson doesn't exist");
		}
		LessonVersionModel currentVersion = lessonVersionDao
		        .findById(lesson.getCurrentVersionId());
		//get previous,next lesson id
		Integer nextId = null,previousId = null;
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
	
	private List<LessonModel> getLessonOfCourse(Integer courseId) throws Exception {
		LessonModel sample = new LessonModel();
		sample.setCourseId(courseId);
		return lessonDao.findByExample(sample);
	}
	

    private List<LessonVersionModel> getVersionOfLesson(Integer lessonId) throws Exception{
    	LessonVersionModel versionSample = new LessonVersionModel();
		versionSample.setLessonId(lessonId);
		return lessonVersionDao.findByExample(versionSample);
    }
}
