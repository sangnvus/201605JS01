package vn.edu.fu.veazy.core.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.edu.fu.veazy.core.common.Const;

public class HttpUtils {
    
    public static String getContextUrl(HttpServletRequest req) {
        if (req == null) return null;
        StringBuffer url = req.getRequestURL();
        if (url == null) return null;
        String uri = req.getRequestURI();
        if (uri == null) return null;
        String ctx = req.getContextPath();
        if (ctx == null) return null;
        return url.substring(0, url.length() - uri.length() + ctx.length());
    }

    public static void addCorsHeader(HttpServletResponse response){
        if (Const.CORS_HEADER_ENABLED) {
            if (!response.containsHeader("Access-Control-Allow-Origin")) {
                response.addHeader("Access-Control-Allow-Origin", "http://localhost:3003");
            }
            if (!response.containsHeader("Access-Control-Allow-Methods")) {
                response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
            }
            if (!response.containsHeader("Access-Control-Allow-Headers")) {
                response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
            }
            if (!response.containsHeader("Access-Control-Allow-Credentials")) {
                response.addHeader("Access-Control-Allow-Credentials", "true");
            }
            if (!response.containsHeader("Access-Control-Max-Age")) {
                response.addHeader("Access-Control-Max-Age", "86400");
            }
        }
    }
}
