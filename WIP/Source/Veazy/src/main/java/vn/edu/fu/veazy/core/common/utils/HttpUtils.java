package vn.edu.fu.veazy.core.common.utils;

import javax.servlet.http.HttpServletResponse;

import vn.edu.fu.veazy.core.common.Const;

public class HttpUtils {

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
