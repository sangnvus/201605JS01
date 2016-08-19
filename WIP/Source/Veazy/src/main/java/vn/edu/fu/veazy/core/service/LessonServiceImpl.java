package vn.edu.fu.veazy.core.service;

import java.util.List;
import java.util.Vector;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.form.CreateLessonForm;
import vn.edu.fu.veazy.core.form.UpdateLessonForm;
import vn.edu.fu.veazy.core.model.LessonModel;
import vn.edu.fu.veazy.core.model.LessonVersionModel;
import vn.edu.fu.veazy.core.model.ReportModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.BriefLessonResponse;
import vn.edu.fu.veazy.core.response.CreateLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonVersionResponse;
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
    private GenericDao<UserModel, Integer> userDao;
	
	@Override
	@Transactional
	public CreateLessonResponse createLesson(Integer creatorId, CreateLessonForm form) throws Exception {
		// get data from form
		LessonModel lesson = new LessonModel();
		lesson.setCourseId(form.getCourseId());
		LessonVersionModel lessonVersion = new LessonVersionModel();
		lessonVersion.setCreatorId(creatorId);
		lessonVersion.setDescription(form.getDescription());
		lessonVersion.setTitle(form.getTitle());
		lessonVersion.setVersion(Const.START_INDEX);
		
		lessonVersion.setArticle(form.getReading());
		lessonVersion.setGrammar(form.getGrammar());
		lessonVersion.setListening(form.getListening());
		lessonVersion.setPractice(form.getPractice());
		lessonVersion.setReading(form.getConversation());
		lessonVersion.setVocabulary(form.getVocabulary());
		
		//set date time
		lesson.setCreateDate(System.currentTimeMillis());
		lessonVersion.setCreateDate(System.currentTimeMillis());
		
		lessonVersion.setState(Const.PUBLISHED);
		
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
		if(lesson == null || lesson.isDeleteFlag()){
			throw new Exception("lesson doesn't exist");
		}
		//get all version in lesson
		
		List<LessonVersionModel> listVersion = getVersionOfLesson(lessonId, Const.PUBLISHED);
				
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
		if(lesson == null || lesson.isDeleteFlag()){
			throw new Exception("lesson doesn't exist");
		}
		lesson.setCourseId(form.getCourseId());
		lessonDao.update(lesson);
		
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
//			if(updatingVersion.getCreatorId() != requesterId){
//				throw new Exception("requester isn't creator");
//			}
            updatingVersion.setCreatorId(requesterId);
			updatingVersion.setUpdateDate(System.currentTimeMillis());
			
			updatingVersion.setArticle(form.getReading());
			updatingVersion.setGrammar(form.getGrammar());
			updatingVersion.setListening(form.getListening());
			updatingVersion.setPractice(form.getPractice());
			updatingVersion.setReading(form.getConversation());
			updatingVersion.setVocabulary(form.getVocabulary());
			
			updatingVersion.setDescription(form.getDescription());
			updatingVersion.setTitle(form.getTitle());
			
			//save to db
			lessonVersionDao.update(updatingVersion);
		}else{
			updatingVersion = new LessonVersionModel();
			updatingVersion.setCreatorId(requesterId);
			updatingVersion.setCreateDate(System.currentTimeMillis());
			updatingVersion.setVersion(
			        getVersionOfLesson(form.getLessonId(), Const.PUBLISHED).size()+1);
			updatingVersion.setLessonId(form.getLessonId());
			updatingVersion.setState(Const.UPDATING);
			
			updatingVersion.setArticle(form.getReading());
			updatingVersion.setGrammar(form.getGrammar());
			updatingVersion.setListening(form.getListening());
			updatingVersion.setPractice(form.getPractice());
			updatingVersion.setReading(form.getConversation());
			updatingVersion.setVocabulary(form.getVocabulary());
			
			updatingVersion.setDescription(form.getDescription());
			updatingVersion.setTitle(form.getTitle());
			
			//save to db
			lessonVersionDao.save(updatingVersion);
		}
	}

	@Override
	@Transactional
	public void publishLessonVersion(Integer requesterId, Integer lessionId) throws Exception {
		LessonModel lesson = lessonDao.findById(lessionId);
		if(lesson == null || lesson.isDeleteFlag()){
			throw new Exception("lesson doesn't exist");
		}
		LessonVersionModel sample = new LessonVersionModel();
		sample.setLessonId(lessionId);
		sample.setState(Const.UPDATING);
		sample.setCreatorId(requesterId);
		List<LessonVersionModel> listUpdating=  lessonVersionDao.findByExample(sample);
		LessonVersionModel updatingVersion = null;
		
		//check if have updating version or not
		if(listUpdating != null && listUpdating.size()>0){
			updatingVersion = listUpdating.get(0);
			
			updatingVersion.setState(Const.PUBLISHED);
			lesson.setCurrentVersionId(updatingVersion.getId());
			
			lessonVersionDao.update(updatingVersion);
			lessonDao.update(lesson);
			
		}else{
			throw new Exception("don't have version can be published");
		}
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
		List<UserModel> listReceiver = userDao.findByExample(sample);
		
		for (UserModel userModel : listReceiver) {
			ReportModel report = new ReportModel();
			report.setSenderId(reporterId);
			report.setReceiverId(userModel.getId());
			report.setContent(content);
			report.setLessonId(lessonId);
			report.setCreateDate(System.currentTimeMillis());
			//save to db
			reportDao.save(report);
		}
	}

	@Override
    @SuppressWarnings("unchecked")
	@Transactional
	public List<BriefLessonResponse> getLessonsOfCourse(Integer courseId) throws Exception {
		String sql = "select * from \"Lesson\" les where les.courseId = " + courseId
		        + " and deleteflag = false";
        List<LessonModel> listLesson = (List<LessonModel>) lessonDao.executeSql(sql, LessonModel.class);
		if(listLesson == null || listLesson.isEmpty()){
		    LOGGER.error(listLesson + ": No lesson for " + courseId);
			return null;
		}
		List<BriefLessonResponse> listResult = new Vector<BriefLessonResponse>();
		for (LessonModel lessonModel : listLesson) {
		    BriefLessonResponse response = new BriefLessonResponse();
			response.setLessonId(String.valueOf(lessonModel.getId()));
			//get title from current version
			LessonVersionModel version = lessonVersionDao
			        .findById(lessonModel.getCurrentVersionId());
			response.setTitle(version.getTitle());
            response.setCourseId(lessonModel.getCourseId());
			response.setVersion(version.getVersion());
            response.setDescription(version.getDescription());
            response.setState(version.getState());
			listResult.add(response);
		}
		return listResult;
	}

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<BriefLessonResponse> getAllLesson() throws Exception {
        String sql = "select * from \"Lesson\" les where deleteflag = false";
        List<LessonModel> listLesson = (List<LessonModel>) lessonDao.executeSql(sql, LessonModel.class);
        if(listLesson == null){
            LOGGER.error(listLesson + ": No lesson");
            return null;
        }
        List<BriefLessonResponse> listResult = new Vector<BriefLessonResponse>();
        if (!listLesson.isEmpty()) {
            for (LessonModel lessonModel : listLesson) {
                BriefLessonResponse response = new BriefLessonResponse();
                response.setLessonId(String.valueOf(lessonModel.getId()));
                //get title from current version
                LessonVersionModel version = lessonVersionDao
                        .findById(Integer.valueOf(lessonModel.getCurrentVersionId()));
                response.setTitle(version.getTitle());
                response.setCourseId(lessonModel.getCourseId());
                response.setVersion(version.getVersion());
                response.setDescription(version.getDescription());
                response.setState(version.getState());
                listResult.add(response);
            }
        }
        return listResult;
    }

	@Override
    @SuppressWarnings("unchecked")
	@Transactional
	public GetLessonResponse getLesson(Integer lessonId, boolean edit) throws Exception {
		LessonModel lesson =  lessonDao.findById(Integer.valueOf(lessonId));
		if(lesson == null || lesson.isDeleteFlag()){
			throw new Exception("lesson doesn't exist");
		}
		LessonVersionModel currentVersion = lessonVersionDao
                .findById(lesson.getCurrentVersionId());
		if (edit) {
		    List<LessonVersionModel> listUpdate = getVersionOfLesson(lessonId, Const.UPDATING);
		    if (listUpdate != null && listUpdate.size() > 0) {
	            currentVersion = listUpdate.get(0);
		    }
		}
        String sql = "select * "
                + "from ( "
                + "select les.id as id, "
                + "lag(les.id) over (order by les.id asc) as previousLessonId, "
                + "lead(les.id) over (order by les.id asc) as nextLessonId "
                + "from \"Lesson\" les "
                + ") x "
                + "where x.id = " + lessonId;
        List<Object[]> listLesson = (List<Object[]>) lessonDao.executeSql(sql, null);
        if(listLesson == null || listLesson.isEmpty()){
            LOGGER.error(listLesson + ": No lesson id = " + lessonId);
            return null;
        }
        Object[] onlyOne = listLesson.get(0);
        return new GetLessonResponse(lesson,currentVersion,(Integer)onlyOne[2],(Integer)onlyOne[1]);
	}

	@Override
    @Transactional
	public void deleteLesson(Integer lessonId) throws Exception {
		LessonModel lesson = lessonDao.findById(lessonId);
		lesson.setDeleteDate(System.currentTimeMillis());
		lesson.setDeleteFlag(true);
		lessonDao.update(lesson);
	}

	private List<LessonVersionModel> getVersionOfLesson(Integer lessonId, Integer state) throws Exception{
		LessonVersionModel versionSample = new LessonVersionModel();
		versionSample.setLessonId(lessonId);
		versionSample.setState(state);
		return lessonVersionDao.findByExample(versionSample);
	}

    @Override
    @Transactional
    public int size() throws Exception {
        return getAllLesson().size();
    }
}
