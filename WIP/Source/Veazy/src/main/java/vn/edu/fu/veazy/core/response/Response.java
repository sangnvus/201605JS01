/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.response;

import java.io.Serializable;
import vn.edu.fu.veazy.utils.JsonUtils;

/**
 *
 * @author Hoang Linh
 */
public class Response implements Serializable {

    private int code;
    private Object data;

    public Response(int code) {
        this.code = code;
    }

    public Response() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtils.toBeautyJson(this);
    }

    public String toResponseJson() {
        return JsonUtils.toJson(this);
    }
}
