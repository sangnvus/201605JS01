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
    
    public static final String URLMAPPING_GET_USER = URL_PREFIX + "/users/{user_id}";
    public static final String URLMAPPING_GET_LIST_USERS = URL_PREFIX + "/users";
    public static final String URLMAPPING_GET_LEARNER_EXAMS = URL_PREFIX + "/users/{user_id}/exams";
    public static final String URLMAPPING_GET_LEARNER_EXAM_STATISTIC = URL_PREFIX + "/users/{user_id}/exams/statistics";
    
    public static final String URLMAPPING_CHANGE_ROLE = URL_PREFIX + "/users/change_roll/{user_id}";
    
    //lesson api
    public static final String URLMAPPING_CREATE_LESSON = URL_PREFIX + "/lessons/new";
    public static final String URLMAPPING_GET_LESSON_VERSION = URL_PREFIX + "/lessons/{lesson_id}/ver{version}";
    public static final String URLMAPPING_UPDATE_LESSON = URL_PREFIX + "/lessons/update/{lesson_id}";
    public static final String URLMAPPING_PUBLISH_LESSON = URL_PREFIX + "/lessons/publish/{lesson_id}";
    public static final String URLMAPPING_REPORT_LESSON = URL_PREFIX + "/lessons/report/{lesson_id}";
    public static final String URLMAPPING_GET_LESSON_COURSE = URL_PREFIX + "/courses/{course_id}/lessons";
    public static final String URLMAPPING_GET_LESSON = URL_PREFIX + "/lessons/{lesson_id}";
    public static final String URLMAPPING_APPROVE_LESSON = URL_PREFIX + "/lessons/{lesson_id}/approve";
    public static final String URLMAPPING_DECLINE_LESSON = URL_PREFIX + "/lessons/{lesson_id}/decline";
    
    //course api
    public static final String URLMAPPING_GET_COURSES = URL_PREFIX + "/courses";
    
    //task api
    
    
    /************************* Content status ***************************/
    
    public static final Integer UPDATING = 1;
    public static final Integer REVIEWING = 2;
    public static final Integer PUBLISHED = 3;
    public static final Integer START_INDEX =1;
    
    /************************* User Role ***************************/
    public static final Integer ADMIN =1;
    public static final Integer CONTENT_MANAGER =2;
    public static final Integer EDITOR =3;
    public static final Integer LEARNER =4;
	
    
}
