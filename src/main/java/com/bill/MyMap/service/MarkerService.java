package com.bill.MyMap.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bill.MyMap.dao.MarkerDao;
import com.bill.MyMap.model.HttpDataTransferObject;
import com.bill.MyMap.model.Marker;
import com.bill.MyMap.util.HttpDataTransferUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarkerService {
	@Autowired
	private HttpDataTransferUtil httpDataTransferUtil;
	@Autowired
	private MarkerDao markerDao;
	
	
	public ResponseEntity<?> getMarker(HttpDataTransferObject reqHDTO) {
		Map<String, Object> resp = new HashMap<>();
		
		Integer sn = httpDataTransferUtil.getTranrqUnderlyingType(reqHDTO, "SN", Integer.class);
		Marker marker = markerDao.findBySn(sn);
		
		resp.put("MARKER", marker);
		return httpDataTransferUtil.boxingResEntity(reqHDTO, resp, HttpStatus.OK);
	}
}
