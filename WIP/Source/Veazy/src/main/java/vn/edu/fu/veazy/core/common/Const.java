package vn.edu.fu.veazy.core.common;

/**
 * Common core constants
 *
 * @author minhnn
 *
 */
public class Const {

    public static final String FINAL_NAME = "Veazy";

    /**
     * *********************** CORS Config *****************************
     */
    public static final String CORS_HEADER_ORIGIN = "Origin";
    public static final boolean CORS_HEADER_ENABLED = true;

    /**
     * *********************** System Config ***************************
     */
    public static final String RESOURCE_URL = "/res";
    public static final Integer EXAM_INSURANCE_TIME = 30;

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

    public static final String URLMAPPING_BAN_USER = URL_PREFIX + "/users/ban/{user_id}";
    public static final String URLMAPPING_UNBAN_USER = URL_PREFIX + "/users/unban/{user_id}";
    public static final String URLMAPPING_GET_USER = URL_PREFIX + "/users/{user_id}";
    public static final String URLMAPPING_GET_CURRENT_USER = URL_PREFIX + "/user";
    public static final String URLMAPPING_UPDATE_CURRENT_USER = URL_PREFIX + "/user/update";
    public static final String URLMAPPING_CHGPWD_CURRENT_USER = URL_PREFIX + "/user/chgpwd";
    public static final String URLMAPPING_FOTGOTPWD = URL_PREFIX + "/user/forgotpwd";
    public static final String URLMAPPING_GET_LEARNER_EXAMS = URL_PREFIX + "/user/exams";
    public static final String URLMAPPING_GET_LIST_USERS = URL_PREFIX + "/users";
    public static final String URLMAPPING_GET_SIZE_USERS = URL_PREFIX + "/users/count";
    public static final String URLMAPPING_GET_LEARNER_EXAM_STATISTIC = URL_PREFIX + "/users/{user_id}/exams/statistics";

    public static final String URLMAPPING_CHANGE_ROLE = URL_PREFIX + "/users/change_roll/{user_id}";
    public static final String URLMAPPING_STATS_SKILL_AVG = URL_PREFIX + "/stats/skill/avg";
    public static final String URLMAPPING_STATS_LAST_EXAM = URL_PREFIX + "/stats/exam/last/{number}";
    public static final String URLMAPPING_STATS_COURSE_AVG = URL_PREFIX + "/stats/course/avg";
    public static final String URLMAPPING_STATS_ALL = URL_PREFIX + "/stats/all";
    public static final String URLMAPPING_STATS_ADMIN_ALL = URL_PREFIX + "/stats/admin/all";

    //lesson api
    /**
     * レッソン�?�作り�?�ＡＰＩ
     */
    public static final String URLMAPPING_CREATE_LESSON = URL_PREFIX + "/lessons/new";

    /**
     * レッソン�?��?ージョン�?��?�得�?�ＡＰＩ
     */
    public static final String URLMAPPING_GET_LESSON_VERSION = URL_PREFIX + "/lessons/{lesson_id}/ver{version}";

    /**
     * レッソン�?�アップデート�?�ＡＰＩ
     */
    public static final String URLMAPPING_DRAFT_LESSON = URL_PREFIX + "/lessons/savedraft/{lesson_id}";

    /**
     * レッソンのアップデートのＡＰＩ
     */
    public static final String URLMAPPING_UPDATE_LESSON = URL_PREFIX + "/lessons/update/{lesson_id}";

    /**
     * レッソン�?�出版�?�ＡＰＩ
     */
    public static final String URLMAPPING_PUBLISH_LESSON = URL_PREFIX + "/lessons/publish/{lesson_id}";

    /**
     * レッソン�?�報告�?�ＡＰＩ
     */
    public static final String URLMAPPING_REPORT_LESSON = URL_PREFIX + "/lessons/report/{lesson_id}";

    /**
     * レベル�?�全部�?�レッソン�?��?�得�?�ＡＰＩ
     */
    public static final String URLMAPPING_GET_LESSON_COURSE = URL_PREFIX + "/courses/{course_id}/lessons";

    /**
     * レッソン�?��?�得�?�ＡＰＩ
     */
    public static final String URLMAPPING_GET_LESSON = URL_PREFIX + "/lessons/{lesson_id}";
    public static final String URLMAPPING_GET_SIZE_LESSON = URL_PREFIX + "/lessons/count";

    /**
     * 全部�?�レッソン�?��?�得�?�ＡＰＩ
     */
    public static final String URLMAPPING_GET_ALL_LESSON = URL_PREFIX + "/lessons";

    /**
     * レッソン�?�削除�?�ＡＰＩ
     */
    public static final String URLMAPPING_DELETE_LESSON = URL_PREFIX + "/lessons/delete/{lesson_id}";

    //course api
    /**
     * 全部�?�レベル�?��?�得�?�ＡＰＩ
     */
    public static final String URLMAPPING_GET_COURSES = URL_PREFIX + "/courses";

    //dictionary
    /**
     * 和越辞書�?�検索�?�ＡＰＩ
     */
    public static final String URLMAPPING_LOOKUP_JAVI = URL_PREFIX + "/dict/javi/";

    /**
     * 越和辞書�?�検索�?�ＡＰＩ
     */
    public static final String URLMAPPING_LOOKUP_VIJA = URL_PREFIX + "/dict/vija/";

    //question api
    public static final String URLMAPPING_CREATE_QUESTION = URL_PREFIX + "/questions/new";
    public static final String URLMAPPING_GET_SIZE_QUESTION = URL_PREFIX + "/questions/count";
    public static final String URLMAPPING_UPDATE_QUESTION = URL_PREFIX + "/questions/update/{question_id}";
    public static final String URLMAPPING_GET_QUESTION = URL_PREFIX + "/questions/{question_id}";
    public static final String URLMAPPING_GET_QUESTION_OF_SKILL = URL_PREFIX + "/skill/{skill_id}/questions";
    public static final String URLMAPPING_GET_QUESTION_OF_COURSE = URL_PREFIX + "/courses/{course_id}/questions";
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
     * レ�?ート�?��?�得�?�ＡＰＩ
     */
    public static final String URLMAPPING_GET_REPORT = URL_PREFIX + "/report/get/{report_id}";

    /**
     * 全部�?�レ�?ート�?��?�得�?�ＡＰＩ
     */
    public static final String URLMAPPING_GET_ALL_REPORT = URL_PREFIX + "/report/all";

    /**
     * レ�?ートを読ん�?�状態�?�変更�?�ＡＰＩ
     */
    public static final String URLMAPPING_READ_REPORT = URL_PREFIX + "/report/read/{report_id}";

    /**
     * レ�?ート�?�削除�?�ＡＰＩ
     */
    public static final String URLMAPPING_DELETE_REPORT = URL_PREFIX + "/report/delete/{report_id}";

    // search
    /**
     * レ�?ート�?�削除�?�ＡＰＩ
     */
    public static final String URLMAPPING_SEARCH_QUESTION = URL_PREFIX + "/search/question";
    /**
     * レ�?ート�?�削除�?�ＡＰＩ
     */
    public static final String URLMAPPING_SEARCH_LESSON = URL_PREFIX + "/search/lesson";

    /**
     * *********************** Content status **************************
     */
    /**
     * レッソン�?�読ん�?�状態
     */
    public static final Integer STATE_UPDATING = 1;

    /**
     * レッソン�?�出版状態
     */
    public static final Integer STATE_PUBLISHED = 2;

    /**
     * レッソン�?�最�?�?�指数
     */
    public static final Integer START_INDEX = 1;

    /**
     * *********************** User Role **************************
     */
    /**
     * 管�?�
     */
    public static final Integer ROLE_ADMIN = 1;

    /**
     * 編集員
     */
    public static final Integer ROLE_EDITOR = 2;

    /**
     * 学習者
     */
    public static final Integer ROLE_LEARNER = 3;

    /**
     * *********************** Question type **************************
     */
    public static final Integer QUESTIONTYPE_ALL = 0;
    public static final Integer QUESTIONTYPE_SINGULAR = 1;
    public static final Integer QUESTIONTYPE_GROUP = 2;

    /**
     * *********************** Question Answer type **************************
     */
    public static final Integer MULTIPLE_CHOICE = 1;

    /**
     * *********************** Question Skill type **************************
     */
    public static final Integer QUESTIONSKILL_VOCABULARY = 1;
    public static final Integer QUESTIONSKILL_GRAMMAR = 2;
    public static final Integer QUESTIONSKILL_LISTENING = 3;
    public static final Integer QUESTIONSKILL_READING = 4;
    
    /**
     * *********************** Forgot password email **************************
     */
    public static final String EMAIL_ADDRESS = "veazysystem@gmail.com";
    public static final String EMAIL_PASSWORD = "veazy123";
    public static final String MAIL_SUBJECT = "Veazy Reset Password";
    public static final String MAIL_CONTENT_PRE = "Hey";
    public static final String MAIL_CONTENT_PRE2 = ",\n"
            + "Someone has requested a new password for your Veazy account.\n";
    public static final String MAIL_CONTENT_POST = "If you didn't make this request then you can safely ignore this email.\n"
            + "Best,\n"
            + "\n"
            + "The Veazy Team";
    public static final String LINK_CONTENT_PRE = "<a href=\"";
    public static final String LINK_CONTENT_POST = "\">Reset Password</a>";
}
