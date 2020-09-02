package com.bill.MyMap.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bill.MyMap.model.BasePojo;
import com.bill.MyMap.model.HttpDataTransferObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * http請求與回覆之參數相關操作的工具類
 * @author 
 * @version 
 * @Date
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Slf4j
@Service
public class HttpDataTransferUtil {
	@Autowired
	private PojoUtil pojoUtil;
	
	private HttpHeaders headers;
	private ObjectMapper jacksonMapper;
	public HttpDataTransferUtil () {
		this.jacksonMapper = new ObjectMapper();
	}
	
	/**
	 * <pre>
	 * 包裝ResponseEntity
	 * @param reqHDTO	HttpDataTransferObject
	 * @param resBodyMap	Map
	 * @param status		HttpStatus
	 * @return responseEntity
	 * @throws JsonProcessingException 
	 */
	public ResponseEntity<String> boxingResEntity(HttpDataTransferObject reqHDTO, Map<String, ? extends Object> resBodyMap, HttpStatus status) {
		String resBodyJsonStr = boxingResContent(reqHDTO, resBodyMap);
		return generateResEntity(resBodyJsonStr, status);
	}
	
	/**
	 * <pre>
	 * 包裝執行成功的ResponseBody
	 * @param reqHdto	HttpDataTransferObject
	 * @param resBodyMap	Map
	 * @return resJsonStr
	 * </pre>
	 * @throws JsonProcessingException 
	 */
	public String boxingResContent(HttpDataTransferObject reqHDTO, Map<String, ? extends Object> resBodyMap) {
		reqHDTO.setData(resBodyMap);;
		String resJsonStr = null;
		try {
			resJsonStr = jacksonMapper.writeValueAsString(reqHDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resJsonStr;
	}
	
	/**
	 * <pre>
	 * 產生含有 header 的 responseEntity
	 * @param resBodyJsonStr	String
	 * @param status		HttpStatus
	 * @return resEntity
	 */
	public ResponseEntity<String> generateResEntity(String resBodyJsonStr, HttpStatus status) {
		return ResponseEntity.status(status).headers(headers).body(resBodyJsonStr);
	}
	
	/**
	 * <pre>
	 * 從 reqHDTO 取出 TRANRQ 裡的基本型態資料
	 * 若資料格式錯誤或無此key則return null
	 * @param <T>
	 * @param reqHDTO	HttpDataTransferObject
	 * @param key		String
	 * @param underlyingType	Class<T>
	 * @return value
	 * </pre>
	 */
	public <T extends Object> T getTranrqUnderlyingType(HttpDataTransferObject reqHDTO, String key, Class<T> underlyingType) {
		Object obj;
		if(StringUtils.isBlank(key)) {
			obj = reqHDTO.getData();
		} else {
			obj = reqHDTO.getData().get(key);
		}
		T value = null;
		try {
			value = (T) obj;
		} catch(ClassCastException cce) {
			log.debug("Get tranrs value from HttpDataTransferObject fail", cce);
		}
		return value;
	}
	
	/**
	 * <pre>
	 * 從 reqHDTO 取出 TRANRQ 裡的 pojo
	 * (key為空的話則是取TRANRQ本身)
	 * 若資料格式錯誤或無此key則return null
	 * @param <T>				BasePojo
	 * @param reqHDTO			HttpDataTransferObject
	 * @param key				String
	 * @param pojoClass			Class<T>
	 * @param mapKeyNamingConventionType	NamingConventionType
	 * @return pojo
	 * </pre>
	 */
	public <T extends BasePojo> T getDataBean(HttpDataTransferObject reqHDTO, String key, Class<T> pojoClass) {
		Object mapObj;
		if(StringUtils.isBlank(key)) {
			mapObj = reqHDTO.getData();
		} else {
			mapObj = reqHDTO.getData().get(key);
		}
		T pojo = null;
		try {
			pojo = pojoUtil.transMap2Bean((Map<String, Object>) mapObj, pojoClass);
		} catch (ClassCastException cce) {
			log.debug("Get trans value from HttpDataTransferObject fail", cce);
		}
		return pojo;
	}
}
