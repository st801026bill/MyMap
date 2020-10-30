package com.bill.MyMap.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bill.MyMap.entity.BasePo;
import com.bill.MyMap.model.BasePojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("unchecked")
@Service
public class PojoUtil {
	
	private ObjectMapper jacksonMapper;
	public PojoUtil () {
		this.jacksonMapper = new ObjectMapper();
		this.jacksonMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}
	
	/**
	 * <pre>
	 * 將Map轉換為Pojo
	 * @param map				Map<String, String>
	 * @param pojoClass			Class<?>
	 * @param mapKeyNamingConventionType	NamingConventionType
	 * </pre>
	 */
	public <T extends BasePojo> T transMap2Bean(Map<String, Object> map, Class<T> pojoClass) {
		if(null == map) {
			return null;
		}
		T pojo = null;
		try {
			pojo = jacksonMapper.convertValue(map, pojoClass);
		} catch (Exception e) {
			log.error("Map轉換Pojo Error", e);
		}
		return pojo;
	}
	
	/**
	 * <pre>
	 * 將 Po 轉換為 Pojo
	 * 
	 * @param <T>
	 * @param <S>
	 * @param po
	 * @return pojo
	 * </pre>
	 */
	public <T extends BasePo, S extends BasePojo> S transPo2Pojo(T pos, String... ignoreFields) {
		if(null == pos) return null;
		List<T> posList = Collections.singletonList(pos);
		List<S> pojos = transPo2Pojo(posList, ignoreFields);
		if(pojos.isEmpty()) {
			return null;
		} else {
			return pojos.get(0);
		}
	}
	
	/**
	 * <pre>
	 * 將 Pos 轉換為 Pojos
	 * 
	 * @param <T>
	 * @param <S>
	 * @param pos
	 * @return pojos
	 * </pre>
	 */
	public <T extends BasePo, S extends BasePojo> List<S> transPo2Pojo(List<T> pos, String... ignoreFields) {
		if(pos.isEmpty()) return new ArrayList<>();
		List<S> pojos = new ArrayList<>();
		try {
			pojos = transPo2Pojo(pos, pos.get(0).getClass(), ignoreFields);
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pojos;
	}
	
	/**
	 * <pre>
	 * 將 Po 轉換為 Pojo
	 * 
	 * @param <T>
	 * @param <S>
	 * @param pos
	 * @param poClass
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * </pre>
	 */
	private <T extends BasePo, S extends BasePojo> List<S> transPo2Pojo(List<T> pos, Class<? extends BasePo> poClass, String... ignoreFields)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		
		String pojoClassName = poClass.getName().replace(".entity.", ".model.") + "jo";
		Class<S> pojoClass = (Class<S>) Class.forName(pojoClassName);
		
		List<S> pojos = new ArrayList<>();
		for(int i=0; i<pos.size(); i++) {
			pojos.add(pojoClass.newInstance());
		}
		
		for(int i=0; i<pos.size(); i++) {
			BeanUtils.copyProperties(pos.get(i), pojos.get(i), ignoreFields);
		}
		
		return pojos;
	}
	
	/**
	 * <pre>
	 * 將 Pojo 轉換為 Po
	 * 
	 * @param <T>
	 * @param <S>
	 * @param pojo
	 * @return po
	 * </pre>
	 */
	public <T extends BasePojo, S extends BasePo> S transPojo2Po(T pojo, String... ignoreFields) {
		if(null == pojo) return null;
		List<T> pojoList = new ArrayList<>();
		pojoList.add(pojo);
		List<S> poList = transPojo2Po(pojoList, ignoreFields);
		if(poList.isEmpty()) {
			return null;
		} else {
			return poList.get(0);
		}
	}
	
	/**
	 * <pre>
	 * 將 Pojos 轉換為 Pos
	 * 
	 * @param <T>
	 * @param <S>
	 * @param pojos
	 * @return pos
	 * </pre>
	 */
	public <T extends BasePojo, S extends BasePo> List<S> transPojo2Po(List<T> pojos, String... ignoreFields) {
		if(pojos.isEmpty()) return new ArrayList<>();
		List<S> pos = new ArrayList<>();
		try {
			pos = transPojo2Po(pojos, pojos.get(0).getClass(), ignoreFields);
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pos;
	}
	
	/**
	 * <pre>
	 * 將 Pojos 轉換為 Pos
	 * 
	 * @param <T>
	 * @param <S>
	 * @param pojos
	 * @param pojoClass
	 * @return pos
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private <T extends BasePojo, S extends BasePo> List<S> transPojo2Po(List<T> pojos, Class<? extends BasePojo> pojoClass, String... ignoreFields)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		
		String poClassName = pojoClass.getName().substring(0, pojoClass.getName().length() - 2).replace(".model.", ".entity.");
		Class<S> poClass = (Class<S>) Class.forName(poClassName);
		
		List<S> pos = new ArrayList<>();
		for(int i=0; i<pojos.size(); i++) {
			pos.add(poClass.newInstance());
		}
		
		for(int i=0; i< pojos.size(); i++) {
			BeanUtils.copyProperties(pojos.get(i), pos.get(i), ignoreFields);
		}
		
		return pos;
	}
	
	/**
	 * <pre>
	 * 將Pojo轉換為Map
	 * @param obj		Object
	 * @param ignoreField	String...
	 * @return map	Map<String, Object>
	 */
	public <T extends BasePojo> Map<String, Object> transBean2Map(T pojo, String... ignoreFields) {
		if(null == pojo) {
			return null;
		}
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<String> ignoreFieldList = Arrays.asList(ignoreFields);
		try {
			map = jacksonMapper.convertValue(pojo, Map.class);
			if(!ignoreFieldList.isEmpty()) {
				for(String ignoreField: ignoreFieldList) {
					map.remove(ignoreField);
				}
			}
		} catch (Exception e) {
			log.error("Pojo轉換Map Error", e);
		}
		return map;
	}
	
	/**
	 * <pre>
	 * 將List<Pojo>轉換為List<Map>
	 * @param <T>
	 * @param pojoList	List
	 * @param ignoreFields	String...
	 * @return
	 */
	public <T extends BasePojo> List<Map<String, Object>> transBean2Map(List<T> pojoList, String... ignoreFields) {
		List<Map<String, Object>> mapList = new ArrayList<>();
		for(T pojo: pojoList) {
			Map<String, Object> map = transBean2Map(pojo, ignoreFields);
			if(null != map) {
				mapList.add(map);
			}
		}
		return mapList;
	}
}
