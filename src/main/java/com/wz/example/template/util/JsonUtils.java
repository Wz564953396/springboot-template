package com.wz.example.template.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	static {

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static Map<String, Object> transMap(String json) throws JsonParseException, IOException {

		assertEmpty(json);

		JavaType jvt = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, Object.class);

		return mapper.readValue(json, jvt);
	}
	
	public static void main(String[] args) throws JsonParseException, IOException {
		String s= "{\"success\":true,\"result\":[{\"sku\":\"111111\",\"price\":\"\",\"mall_price\":\"商城售价\"}]}";
		Map<String,Object> requestResult = transMap(s);
		List<Map<String,Object>> ll = (List<Map<String,Object>>) requestResult.get("result");
		
		
		System.out.println(ll.get(0).get("price") instanceof Integer);
	}

	private static void assertEmpty(String json) throws JsonParseException {

		if(StringUtils.isEmpty(json)) {
			throw new JsonParseException(null, json);
		}
	}

	public static List<Map<String, Object>> transList(String json) throws JsonParseException, IOException {

		assertEmpty(json);

		JavaType jvt = mapper.getTypeFactory().constructParametricType(ArrayList.class, HashMap.class);

		return mapper.readValue(json, jvt);
	}
	
	public static <T> List<T> transList(String json, Class<T> cls) throws JsonParseException, IOException {

		assertEmpty(json);

		JavaType jvt = mapper.getTypeFactory().constructParametricType(ArrayList.class, cls);

		return mapper.readValue(json, jvt);
	}

	public static <T> T transBean(String json, Class<T> clazz) throws JsonParseException, IOException {

		return mapper.readValue(json, clazz);
	}

	public static String transString(Object value) throws JsonProcessingException {

		return mapper.writeValueAsString(value);
	}

	/**
	 * 构造服务器异常返回结果
	 * 
	 * @param desc   错误描述
	 * @return
	 */
	public static String errorDesc(String desc) {

		Map<String, Object> map = new HashMap<>();
		map.put("desc", desc);
		map.put("success", false);

		try {
			return mapper.writeValueAsString(map);
		} catch(JsonProcessingException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("{\"success\":false,\"desc\":\"").append(desc).append("\"}");
			return sb.toString();
		}
	}
	
	/**
	 * 构造服务器异常返回结果
	 * 
	 * @param desc   错误描述
	 * @return
	 */
	public static String errorInfo(String desc) {

		Map<String, Object> map = new HashMap<>();
		map.put("errorInfo", desc);
		map.put("result", "false");

		try {
			return mapper.writeValueAsString(map);
		} catch(JsonProcessingException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("{\"result\":\"false\",\"errorInfo\":\"").append(desc).append("\"}");
			return sb.toString();
		}
	}
}
