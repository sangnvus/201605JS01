package vn.edu.fu.veazy.core.common;

import java.util.regex.Pattern;

/**
 * Core utilities
 * 
 * @author minhnn
 *
 */
public class Utils {
    private static Pattern emailPattern;
    private static final String REGEX_EMAIL_PATTERN = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?:[A-Z]{2}"
            + "|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum|ru|vn|jp|us|ca|cn)\\b$";

    static {
        emailPattern = Pattern.compile(REGEX_EMAIL_PATTERN);
    }

    /**
     * Check if a given email is valid or not
     * 
     * @param emailAddress email address
     * @return true if valid; false otherwise.
     */
    public static boolean isValidEmail(String emailAddress) {
        return emailPattern.matcher(emailAddress).find();
    }
}
