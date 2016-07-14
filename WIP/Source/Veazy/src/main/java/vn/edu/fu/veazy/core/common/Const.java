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
    public static final String URLMAPPING_RESET_PASSWORD = URL_PREFIX + "/users/reset_password";
    
    public static final String URLMAPPING_GET_USER = URL_PREFIX + "/users/{user_id}";
    public static final String URLMAPPING_GET_LIST_USERS = URL_PREFIX + "/users";
    /**
     * URL to lesson detail page
     * 
     * @PathVariable lessonId lesson id
     */
    public static final String URLMAPPING_LESSON_DETAIL = URL_PREFIX + "/lesson/{lessonId}";

    
    public static final Integer PUBLISHED = 1;
    public static final Integer UPDATING = 2;
    public static final Integer REVIEWING = 3;
    public static final Integer START_INDEX =1;
}
