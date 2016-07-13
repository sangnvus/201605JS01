/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.ntqsolution.dotconductor.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import vn.com.ntqsolution.dotconductor.constant.Constants;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import vn.com.ntqsolution.dotconductor.exception.DotConductorException;

/**
 *
 * @author Rua
 */
public class JsonUtils {

    private static final Logger logger = LogManager.getLogger();
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
                logger.error("Error convert object to json", ex);
            }
        }
        return json;
    }

    public static String toBeautyJson(Object object) throws DotConductorException {
        String json = "";
        if (object != null) {
            try {
                ObjectWriter ow = mapper.writerWithDefaultPrettyPrinter();
                json = ow.writeValueAsString(object);
            } catch (JsonProcessingException ex) {
                throw new DotConductorException(ex);
            }
        }
        return json;
    }

    public static <T> T toObject(String json, Class<T> type) throws DotConductorException {
        T t = null;
        if (json != null && !json.equals("")) {
            try {
                t = mapper.readValue(json, type);
            } catch (IOException ex) {
                throw new DotConductorException("Cannot convert Json to Object type " + type.toString(), ex);
            }

        }
        return t;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> toMap(String json, boolean readonly) throws DotConductorException {
        Map<K, V> map;
        if (readonly) {
            map = Collections.EMPTY_MAP;
        } else {
            map = new HashMap<>();
        }
        if (json != null && !json.equals("")) {
            try {
                map = mapper.readValue(json, HashMap.class);
            } catch (IOException ex) {
                throw new DotConductorException("Cannot convert Json to Object type ", ex);
            }
        }
        return map;
    }

    public static Object toObject(String json, JavaType type) throws DotConductorException {
        Object object = null;
        if (json != null && !json.equals("")) {
            try {
                object = mapper.readValue(json, type);
            } catch (IOException ex) {
                throw new DotConductorException("Cannot convert Json to Object type " + type.toString(), ex);
            }
        }
        return object;
    }

    @SuppressWarnings("rawtypes")
    public static <T extends Collection> T toCollection(String json, Class<T> collectionType, Class<?> elememtType)
            throws DotConductorException {
        T t = null;
        JavaType type = constructCollectionType(collectionType, elememtType);
        if (json != null && !json.equals("")) {
            try {
                t = mapper.readValue(json, type);
            } catch (IOException ex) {
                logger.error(ex);
                throw new DotConductorException("Cannot convert Json to Object type " + type.toString(), ex);
            }
        }
        return t;
    }

    public static JavaType constructCollectionType(
            @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<?> elementType) {
        JavaType type = mapper.getTypeFactory().constructCollectionType(collectionClass, elementType);
        return type;
    }

    public static JavaType constructCollectionType(Class<? extends Collection<?>> collectionClass, JavaType elementType) {
        JavaType type = mapper.getTypeFactory().constructCollectionType(collectionClass, elementType);
        return type;
    }

    public static Object convertMapToObject(Map<String, String> userInfoString, Class<?> clazz) {
        return mapper.convertValue(userInfoString, clazz);
    }

    public static JsonNode getRootNodeOfJsonString(String jsonString) throws JsonProcessingException, IOException {
        return mapper.readTree(jsonString);
    }

    public static <T> T toObject(String json, TypeReference<T> typeReference) throws DotConductorException {
        if (json == null) {
            return null;
        }
        try {
            T ret = mapper.readValue(json, typeReference);
            return ret;
        } catch (IOException ex) {
            throw new DotConductorException(ex);
        }
    }

    public static ObjectNode createObjectNode(String json) throws DotConductorException {
        if (json == null || json.isEmpty()) {
            return mapper.createObjectNode();
        }
        try {
            ObjectNode node = mapper.readValue(json, ObjectNode.class);
            return node;
        } catch (IOException ex) {
            throw new DotConductorException(ex);
        }
    }

    public static ObjectNode putValueToObjectNode(ObjectNode node, String key, String value) {
        return node.put(key, value);
    }

    public static ObjectNode setObjectNodeValue(ObjectNode node, String key, String value) {
        return putValueToObjectNode(node, key, value);
    }

    public static String getFieldValue(ObjectNode node, String fieldName) {
        JsonNode jNode = node.get(fieldName);
        if (jNode == null) {
            return null;
        }
        String value = jNode.toString();
        if (value.equals("null")) {
            return null;
        }
        return value.substring(1, value.length() - 1);
    }

    public static int parseIntFromParam(JSONObject request, String paramName) throws DotConductorException {
        Object param = request.get(paramName);
        int paramValue = Constants.INVALID_INT_VALUE;
        if (Validator.isNotNull(param)) {
            try {
                paramValue = Integer.parseInt(param.toString());
            } catch (NumberFormatException ex) {
                throw new DotConductorException();
            }
        }
        return paramValue;
    }

    public static String parseStringFromParams(JSONObject request, String paramName) throws DotConductorException {
        String result = null;
        try {
            result = (String) request.get(paramName);
        } catch (Exception ex) {
            throw new DotConductorException();
        }
        return result;
    }
}
