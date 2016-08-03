package vn.edu.fu.veazy.core.common;

/**
 * Common core constants
 *
 * @author minhnn
 *
 */
public class Const {

    /**
     * *********************** CORS Config *****************************
     */
    public static final boolean CORS_HEADER_ENABLED = true;
    
    /************************* System Config ****************************/
    public static final String RESOURCE_URL = "/res";

    /**
     * *********************** URL Mapping *****************************
     */
    public static final String URLMAPPING_MAKE_ADMIN = "/mkad/{user_id}";
    public static final String URLMAPPING_MAKE_EDITOR = "/mked/{user_id}";
    /**
     * URL to home page
     */
    public static final String URLMAPPING_HOME = "/";
    public static final String URLMAPPING_UPLOADFILE = "/uploadfile";
    public static final String URL_PREFIX = "/api";
    public static final String URLMAPPING_REGISTER = URL_PREFIX + "/register";
    public static final String URLMAPPING_LOGIN = URL_PREFIX + "/login";
    public static final String URLMAPPING_LOGOUT = URL_PREFIX + "/logout";
    public static final String URLMAPPING_RESET_PASSWORD = URL_PREFIX + "/users/reset_password";

    public static final String URLMAPPING_GET_USER = URL_PREFIX + "/users/{user_id}";
    public static final String URLMAPPING_GET_CURRENT_USER = URL_PREFIX + "/user";
    public static final String URLMAPPING_UPDATE_CURRENT_USER = URL_PREFIX + "/user/update";
    public static final String URLMAPPING_CHGPWD_CURRENT_USER = URL_PREFIX + "/user/chgpwd";
    public static final String URLMAPPING_GET_LIST_USERS = URL_PREFIX + "/users";
    public static final String URLMAPPING_GET_LEARNER_EXAMS = URL_PREFIX + "/users/{user_id}/exams";
    public static final String URLMAPPING_GET_LEARNER_EXAM_STATISTIC = URL_PREFIX + "/users/{user_id}/exams/statistics";

    public static final String URLMAPPING_CHANGE_ROLE = URL_PREFIX + "/users/change_roll/{user_id}";

    //lesson api
    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?Æ‰Ωú„Çä„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_CREATE_LESSON = URL_PREFIX + "/lessons/new";

    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?Æ„É?„Éº„Ç∏„Éß„É≥„?ÆÂ?ñÂæó„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_GET_LESSON_VERSION = URL_PREFIX + "/lessons/{lesson_id}/ver{version}";

    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?Æ„Ç¢„ÉÉ„Éó„Éá„Éº„Éà„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_DRAFT_LESSON = URL_PREFIX + "/lessons/savedraft/{lesson_id}";
    
    /**
     * „É¨„ÉÉ„ÇΩ„É≥„ÅÆ„Ç¢„ÉÉ„Éó„Éá„Éº„Éà„ÅÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_UPDATE_LESSON = URL_PREFIX + "/lessons/update/{lesson_id}";

    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?ÆÂá∫Áâà„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_PUBLISH_LESSON = URL_PREFIX + "/lessons/publish/{lesson_id}";

    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?ÆÂ†±Âëä„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_REPORT_LESSON = URL_PREFIX + "/lessons/report/{lesson_id}";

    /**
     * „É¨„Éô„É´„?ÆÂÖ®ÈÉ®„?Æ„É¨„ÉÉ„ÇΩ„É≥„?ÆÂ?ñÂæó„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_GET_LESSON_COURSE = URL_PREFIX + "/courses/{course_id}/lessons";

    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?ÆÂ?ñÂæó„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_GET_LESSON = URL_PREFIX + "/lessons/{lesson_id}";

    /**
     * ÂÖ®ÈÉ®„?Æ„É¨„ÉÉ„ÇΩ„É≥„?ÆÂ?ñÂæó„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_GET_ALL_LESSON = URL_PREFIX + "/lessons";

    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?ÆÂâäÈô§„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_DELETE_LESSON = URL_PREFIX + "/lessons/delete/{lesson_id}";

    //course api
    /**
     * ÂÖ®ÈÉ®„?Æ„É¨„Éô„É´„?ÆÂ?ñÂæó„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_GET_COURSES = URL_PREFIX + "/courses";

    //dictionary
    /**
     * ÂíåË∂äËæûÊõ∏„?ÆÊ§úÁ¥¢„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_LOOKUP_JAVI = URL_PREFIX + "/dict/javi/";

    /**
     * Ë∂äÂíåËæûÊõ∏„?ÆÊ§úÁ¥¢„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_LOOKUP_VIJA = URL_PREFIX + "/dict/vija/";

    //question api
    public static final String URLMAPPING_CREATE_QUESTION = URL_PREFIX + "/questions/new";
    public static final String URLMAPPING_UPDATE_QUESTION = URL_PREFIX + "/questions/update/{question_id}";
    public static final String URLMAPPING_GET_QUESTION = URL_PREFIX + "/questions/{question_id}";
    public static final String URLMAPPING_GET_LIST_QUESTIONS = URL_PREFIX + "/questions";
    public static final String URLMAPPING_DELETE_QUESTION = URL_PREFIX + "/questions/delete/{question_id}";
    public static final String URLMAPPING_REPORT_QUESTION = URL_PREFIX + "/questions/report/{question_id}";

    //exam api 
    public static final String URLMAPPING_CREATE_EXAM = URL_PREFIX + "/exams";
    public static final String URLMAPPING_SUBMIT_EXAM_ANSWER = URL_PREFIX + "/exams/submit";
    public static final String URLMAPPING_GET_EXAM = URL_PREFIX + "/exams/{exam_id}";
    public static final String URLMAPPING_REDO_EXAM = URL_PREFIX + "/exams/{exam_id}/redo";

    //report api
    /**
     * „É¨„É?„Éº„Éà„?ÆÂ?ñÂæó„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_GET_REPORT = URL_PREFIX + "/report/get/{report_id}";

    /**
     * ÂÖ®ÈÉ®„?Æ„É¨„É?„Éº„Éà„?ÆÂ?ñÂæó„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_GET_ALL_REPORT = URL_PREFIX + "/report/all";

    /**
     * „É¨„É?„Éº„Éà„ÇíË™≠„Çì„?†Áä∂ÊÖã„?´Â§âÊõ¥„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_READ_REPORT = URL_PREFIX + "/report/read/{report_id}";

    /**
     * „É¨„É?„Éº„Éà„?ÆÂâäÈô§„?ÆÔº°Ôº∞Ôº©
     */
    public static final String URLMAPPING_DELETE_REPORT = URL_PREFIX + "/report/delete/{report_id}";

    /**
     * *********************** Content status **************************
     */
    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?ÆË™≠„Çì„?†Áä∂ÊÖã
     */
    public static final Integer UPDATING = 1;

    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?ÆÂá∫ÁâàÁä∂ÊÖã
     */
    public static final Integer PUBLISHED = 2;

    /**
     * „É¨„ÉÉ„ÇΩ„É≥„?ÆÊúÄÂà?„?ÆÊåáÊï∞
     */
    public static final Integer START_INDEX = 1;

    /**
     * *********************** User Role **************************
     */
    /**
     * ÁÆ°Á?Ü
     */
    public static final Integer ROLE_ADMIN = 1;

    /**
     * Á∑®ÈõÜÂì°
     */
    public static final Integer ROLE_EDITOR = 2;

    /**
     * Â≠¶ÁøíËÄÖ
     */
    public static final Integer ROLE_LEARNER = 3;

    /**
     * *********************** Question type **************************
     */
    public static final Integer ALL = 0;
    public static final Integer SINGULAR = 1;
    public static final Integer GROUP = 2;

    /**
     * *********************** Question Answer type **************************
     */
    public static final Integer MULTIPLE_CHOICE = 1;

    /**
     * *********************** Question Skill type **************************
     */
    public static final Integer VOCABULARY = 1;
    public static final Integer GRAMMAR = 2;
    public static final Integer READING = 3;
    public static final Integer LISTENING = 4;

}
