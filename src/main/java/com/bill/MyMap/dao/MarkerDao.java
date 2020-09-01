package com.bill.MyMap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.MyMap.model.Marker;
import com.bill.MyMap.repository.MarkerRepository;

@Service
public class MarkerDao {
	@Autowired
	MarkerRepository markerRepository;
	public Marker findBySn(Integer sn) {
		return markerRepository.findBySn(sn);
	}
}
