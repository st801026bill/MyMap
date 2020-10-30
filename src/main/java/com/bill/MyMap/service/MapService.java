package com.bill.MyMap.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bill.MyMap.dao.MarkerDao;
import com.bill.MyMap.dao.MarkerKindDao;
import com.bill.MyMap.model.HttpDataTransferObject;
import com.bill.MyMap.model.MarkerKindPojo;
import com.bill.MyMap.model.MarkerPojo;
import com.bill.MyMap.util.HttpDataTransferUtil;
import com.bill.MyMap.util.PojoUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MapService {
	@Autowired
	private HttpDataTransferUtil httpDataTransferUtil;
	@Autowired
	private MarkerDao markerDao;
	@Autowired
	private MarkerKindDao markerKindDao;
	
	@Autowired
	PojoUtil pojoUtil;
	
	public ResponseEntity<?> getMarker(HttpDataTransferObject reqHDTO) {
		Map<String, Object> resp = new HashMap<>();
		
		Integer sn = httpDataTransferUtil.getTranrqUnderlyingType(reqHDTO, "SN", Integer.class);
		MarkerPojo marker = markerDao.findBySn(sn);
		
		resp.put("MARKER", marker);
		return httpDataTransferUtil.boxingResEntity(reqHDTO, resp, HttpStatus.OK);
	}
	
	public ResponseEntity<?> queryAllMarker(HttpDataTransferObject reqHDTO) {
		List<MarkerPojo> markers = markerDao.findAll();
		
		Map<String, Object> resp = new HashMap<>();
		resp.put("MARKERS", markers);
		return httpDataTransferUtil.boxingResEntity(reqHDTO, resp, HttpStatus.OK);
	}
	
	public ResponseEntity<?> addMarker(HttpDataTransferObject reqHDTO) {		
		MarkerPojo marker = httpDataTransferUtil.getDataBean(reqHDTO, "", MarkerPojo.class);
		
		//新增 點位資料
		MarkerPojo pojo = markerDao.addMarker(marker);
		log.info(pojo.toString());

		Map<String, Object> resp = new HashMap<>();
		resp.put("MARKER", pojo);
		return httpDataTransferUtil.boxingResEntity(reqHDTO, resp, HttpStatus.OK);
	}
	
	public ResponseEntity<?> queryByKind(HttpDataTransferObject reqHDTO) {		
		String countryId = httpDataTransferUtil.getTranrqUnderlyingType(reqHDTO, "COUNTRY_ID", String.class);
		String cityId = httpDataTransferUtil.getTranrqUnderlyingType(reqHDTO, "CITY_ID", String.class);
		
		List<MarkerPojo> pojo;
		if(StringUtils.equals("A", countryId)) 
			pojo = markerDao.findAll();
		else if(!StringUtils.equals("A", countryId) && StringUtils.equals("A", cityId))
			pojo = markerDao.findByCountryId(countryId);
		else
			pojo = markerDao.findByCountryIdAndCityId(countryId, cityId);
		
		Map<String, Object> resp = new HashMap<>();
		resp.put("MARKERS", pojo);
		return httpDataTransferUtil.boxingResEntity(reqHDTO, resp, HttpStatus.OK);
	}
	
	public ResponseEntity<?> queryMarkerKindDDL(HttpDataTransferObject reqHDTO) {
		String countryID = httpDataTransferUtil.getTranrqUnderlyingType(reqHDTO, "COUNTRY_ID", String.class);
		List<MarkerKindPojo> result;
		if(StringUtils.equals(countryID, ""))
			result = markerKindDao.findCountryDDL();
		else
			result = markerKindDao.findCityDDL(countryID);
		
		//List<Map<String, Object>> resp = pojoUtil.transBean2Map(result ,"");
		Map<String, Object> resp = new HashMap();
		resp.put("KIND", result);
		return httpDataTransferUtil.boxingResEntity(reqHDTO, resp, HttpStatus.OK);
	}
}
