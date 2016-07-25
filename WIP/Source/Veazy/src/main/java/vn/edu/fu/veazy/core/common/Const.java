package vn.edu.fu.veazy.core.common;

/**
 * Common core constants
 * 
 * @author minhnn
 *
 */
public class Const {

    /************************* URL Mapping ******************************/
    /**
     * URL to home page
     */
    public static final String URLMAPPING_HOME = "/";
    public static final String URL_PREFIX = "/api";
    public static final String URLMAPPING_REGISTER = URL_PREFIX + "/register";
    public static final String URLMAPPING_LOGIN = URL_PREFIX + "/login";
    public static final String URLMAPPING_LOGOUT = URL_PREFIX + "/logout";
    public static final String URLMAPPING_RESET_PASSWORD = URL_PREFIX + "/users/reset_password";
    
    //user api
    public static final String URLMAPPING_GET_USER = URL_PREFIX + "/users/{user_id}";
    public static final String URLMAPPING_GET_LIST_USERS = URL_PREFIX + "/users";
    public static final String URLMAPPING_GET_LEARNER_EXAMS = URL_PREFIX + "/users/{user_id}/exams";
    public static final String URLMAPPING_GET_LEARNER_EXAM_STATISTIC = URL_PREFIX + "/users/{user_id}/exams/statistics";
    
    //lesson api
    public static final String URLMAPPING_CREATE_LESSON = URL_PREFIX + "/lessons";
    public static final String URLMAPPING_GET_LESSON_VERSION = URL_PREFIX + "/lessons/{lesson_id}/{version}";
    public static final String URLMAPPING_UPDATE_LESSON = URL_PREFIX + "/lessons/{lesson_id}";
    public static final String URLMAPPING_PUBLISH_LESSON = URL_PREFIX + "/lessons/{lesson_id}/publish_lesson";
    public static final String URLMAPPING_REPORT_LESSON = URL_PREFIX + "/lessons/{lesson_id}/report";
    public static final String URLMAPPING_GET_LESSON_COURSE = URL_PREFIX + "/courses/{course_id}/lessons";
    public static final String URLMAPPING_GET_LESSON = URL_PREFIX + "/lessons/{lesson_id}";
    
    //course api
    public static final String URLMAPPING_GET_COURSES = URL_PREFIX + "/courses";
    
    //question api
    public static final String URLMAPPING_CREATE_QUESTION = URL_PREFIX + "/questions";
    public static final String URLMAPPING_UPDATE_QUESTION = URL_PREFIX + "/questions/{quetsion_id}";
    public static final String URLMAPPING_GET_QUESTION = URL_PREFIX + "/questions/{quetsion_id}";
    public static final String URLMAPPING_GET_LIST_QUESTIONS = URL_PREFIX + "/questions";
    public static final String URLMAPPING_DELETE_QUESTION = URL_PREFIX + "/questions/{quetsion_id}";
    public static final String URLMAPPING_REPORT_QUESTION = URL_PREFIX + "/questions/{quetsion_id}/report";
    
    /************************* User role ***************************/
    
    public static final Integer LEARNER = 1;
    public static final Integer CONTENT_CREATOR = 2;
    public static final Integer CONTENT_MANAGER = 3;
    public static final Integer ADMIN = 4;
    
    /************************* Content status ***************************/
    
    public static final Integer PUBLISHED = 1;
    public static final Integer UPDATING = 2;
    public static final Integer REVIEWING = 3;
    public static final Integer START_INDEX = 2;
    
    /************************* Question type ***************************/
    
    public static final Integer ALL = 0;
    public static final Integer SINGULAR = 1;
    public static final Integer GROUP = 2;
    
    /************************* Question Answer type ***************************/
    
    public static final Integer MULTIPLE_CHOICE = 1;
    
    /************************* Question Skill type ***************************/
    
    public static final Integer VOCABULARY = 1;
    public static final Integer GRAMMAR = 2;
    public static final Integer READING = 3;
    public static final Integer LISTENING = 4;
}
