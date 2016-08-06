/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response;

/**
 *
 * @author Hoang Linh
 */
public class ResponseCode {

    public static final int SUCCESS = 200;
    public static final int CREATED = 201;

    public static final int FOUND = 302;

    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int PERMISSION_DENIED = 403;
    public static final int REQUEST_TIMEOUT = 408;

    public static final int INVALID_EMAIL = 410;
    public static final int DUPLICATED_EMAIL = 411;
    public static final int EMAIL_NOT_FOUND = 412;
    public static final int DUPLICATED_USERNAME = 413;

    public static final int CORRUPTED_FORM = 460;
    public static final int USERNAME_EXPECTED = 461;
    public static final int PASSWORD_EXPECTED = 462;
    public static final int EMAIL_EXPECTED = 463;
    public static final int NULL_EMPTY_CONTENT = 464;

    public static final int INVALID_PASSWORD = 420;
    public static final int INCORRECT_PASSWORD = 421;

    public static final int USER_NOT_FOUND = 430;
    public static final int USER_NOT_ALLOW = 431;
    public static final int USER_INACTIVE = 432;
    public static final int USER_EXAMS_NOT_FOUND = 433;

    public static final int USERNAME_PASSWORD_NOT_MATCH = 470;
    public static final int PASSWORD_INCORRECT = 471;

    public static final int QUESTION_NOT_FOUND = 440;
    public static final int INTERNAL_SERVER_ERROR = 500;

    public static final int UNSUPPORTED_API = 600;

}
