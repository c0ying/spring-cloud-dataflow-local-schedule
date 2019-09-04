package com.jingxin.cloud.dataflow.support.scheduler.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json解析工具
 * @author cyh
 *
 */
public class JsonUtil {
	
	public static ObjectMapper objectMapper = new ObjectMapper();

	
	/**
	 * 解析Json到指定的Bean中
	 * @param jsonStr
	 * @param clz
	 * @return
	 */
	public static <T> T parseJson(String jsonStr, Class<T> clz){
		try {
			T bean = objectMapper.readValue(jsonStr, clz);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> List<T> parseListJson(String jsonStr, Class<T> clz){
		try {
			JavaType javaType = 
					objectMapper.getTypeFactory()
					.constructParametrizedType(ArrayList.class, List.class, clz);
			List<T> bean = objectMapper.readValue(jsonStr, javaType);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, Object> parse2Map(String jsonStr){
		try {
			return objectMapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static String toJson(Object object){
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
   public static Object parseJson(String json, Class<?> wrap, Class<?>... inner){
        JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(wrap, wrap, inner);
        try {
            return objectMapper.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }
   
   public static JsonNode parse2Tree(String jsonStr){
		try {
			return objectMapper.readTree(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
