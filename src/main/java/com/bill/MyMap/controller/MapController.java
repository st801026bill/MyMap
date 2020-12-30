package com.bill.MyMap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bill.MyMap.model.HttpDataTransferObject;
import com.bill.MyMap.service.MapService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/marker")
public class MapController {
	@Autowired
	private MapService mapService;
	
	/*
	 * 1. 取得指定Marker
	 */
	@RequestMapping(value="/query",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> queryMarker(@RequestBody HttpDataTransferObject reqHDTO) {
		log.info("Got Request body:{}", reqHDTO);
		return mapService.getMarker(reqHDTO);
	}
	
	/*
	 * 2. 取得所有Marker
	 */
	@RequestMapping(value="/queryAll",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> queryAllMarker(@RequestBody HttpDataTransferObject reqHDTO) {
		log.info("Got Request body:{}", reqHDTO);
		return mapService.queryAllMarker(reqHDTO);
	}
	
	/*
	 * 3.新增/修改指定Marker
	 */
	@RequestMapping(value="/save",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveMarker(@RequestBody HttpDataTransferObject reqHDTO) {
		log.info("Got Request body:{}", reqHDTO);
		return mapService.saveMarker(reqHDTO);
	}
	
	/*
	 * 4. 刪除指定Marker
	 */
	@RequestMapping(value="/delete",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteMarker(@RequestBody HttpDataTransferObject reqHDTO) {
		log.info("Got Request body:{}", reqHDTO);
		return mapService.deleteMarker(reqHDTO);
	}
	
	/*
	 * 5 依分類取得Marker
	 */
	@RequestMapping(value="/queryByKind",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> queryByKind(@RequestBody HttpDataTransferObject reqHDTO) {
		log.info("Got Request body:{}", reqHDTO);
		return mapService.queryByKind(reqHDTO);
	}
	
	/*
	 * 6. 取得分類下拉
	 */
	@RequestMapping(value="/kindDDL",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> queryMarkerKindDDL(@RequestBody HttpDataTransferObject reqHDTO) {
		log.info("Got Request body:{}", reqHDTO);
		return mapService.queryMarkerKindDDL(reqHDTO);
	}
}
