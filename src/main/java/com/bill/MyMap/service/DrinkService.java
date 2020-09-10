package com.bill.MyMap.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bill.MyMap.dao.DrinkDao;
import com.bill.MyMap.model.DrinkPojo;
import com.bill.MyMap.model.HttpDataTransferObject;
import com.bill.MyMap.util.HttpDataTransferUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DrinkService {
	@Autowired
	private HttpDataTransferUtil httpDataTransferUtil;
	@Autowired
	private DrinkDao drinkDao;
	
	
	public ResponseEntity<?> getDrink(HttpDataTransferObject reqHDTO) {
		Map<String, Object> resp = new HashMap<>();
		
		String name = httpDataTransferUtil.getTranrqUnderlyingType(reqHDTO, "NAME", String.class);
		DrinkPojo drink = drinkDao.findByName(name);
		
		resp.put("DRINK", drink);
		return httpDataTransferUtil.boxingResEntity(reqHDTO, resp, HttpStatus.OK);
	}
}
