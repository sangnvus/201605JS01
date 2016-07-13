/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.common;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 *
 * @author Hoang Linh
 */
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .setSerializationInclusion(Include.NON_NULL);
    
    private JsonUtils() {
    }

    public static String toJson(Object object) {
        String json = "";
        if (object != null) {
            try {
                json = mapper.writeValueAsString(object);
            } catch (JsonProcessingException ex) {
            }
        }
        return json;
    }
    
    public static String toBeautyJson(Object object){
        String json = "";
        if (object != null) {
            try {
                ObjectWriter ow = mapper.writerWithDefaultPrettyPrinter();
                json = ow.writeValueAsString(object);
            } catch (JsonProcessingException ex) {
            }
        }
        return json;
    }
}
