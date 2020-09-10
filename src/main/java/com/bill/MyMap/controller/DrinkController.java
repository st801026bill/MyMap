package com.bill.MyMap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bill.MyMap.model.HttpDataTransferObject;
import com.bill.MyMap.service.DrinkService;
import com.bill.MyMap.service.MarkerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/drink")
public class DrinkController {
	@Autowired
	private DrinkService drinkService;
	
	
	@RequestMapping(value="/query",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> queryMarker(@RequestBody HttpDataTransferObject reqHDTO) {
		
		log.info("Got Request body:{}", reqHDTO);
		return drinkService.getDrink(reqHDTO);
	}
}
