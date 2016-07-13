package vn.edu.fu.veazy.cpanel.common;

/**
 * Common cpanel constants
 * 
 * @author minhnn
 *
 */
public class Const {

    /************************* URL Mapping ******************************/
    // User Control Panel
    /**
     * URL to user cp page
     */
    public static final String URLMAPPING_USER_CP = "/usercp";
    
    // Editor Control Panel
    /**
     * URL to editor cp page
     */
    public static final String URLMAPPING_EDITOR_CP = "/editorcp";
    /**
     * URL to editor lesson detail page
     * 
     * @PathVariable {lessonId} lesson id
     */
    public static final String URLMAPPING_EDITOR_LESSON_DETAIL = "/editorcp/lesson/{lessonId}";
    
    // Content Manager Control Panel
    /**
     * URL to cm cp page
     */
    public static final String URLMAPPING_CM_CP = "/cmcp";
    /**
     * URL to cm lesson detail page
     * 
     * @PathVariable {lessonId} lesson id
     */
    public static final String URLMAPPING_CM_LESSON_DETAIL = "/cmcp/lesson/{lessonId}";
    
    // Administrator Control Panel
    /**
     * URL to admin cp page
     */
    public static final String URLMAPPING_AD_CP = "/admincp";

}
