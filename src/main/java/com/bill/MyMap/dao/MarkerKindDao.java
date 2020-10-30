package com.bill.MyMap.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bill.MyMap.entity.MarkerKindPo;
import com.bill.MyMap.entity.MarkerPo;
import com.bill.MyMap.enums.ErrorType;
import com.bill.MyMap.exception.ModuleException;
import com.bill.MyMap.model.MarkerKindPojo;
import com.bill.MyMap.model.MarkerPojo;
import com.bill.MyMap.repository.MarkerKindRepository;
import com.bill.MyMap.util.PojoUtil;

@Service
public class MarkerKindDao {
	@Autowired
	MarkerKindRepository markerKindRepository;
	@Autowired
	PojoUtil pojoUtil;
	
	public MarkerKindPojo findBySn(Integer sn) {
		MarkerKindPo po = markerKindRepository.findBySn(sn);
		return pojoUtil.transPo2Pojo(po, "");
	}

	public List<MarkerKindPojo> findAll() {
		return pojoUtil.transPo2Pojo(markerKindRepository.findAll());
	}
	
	public List<MarkerKindPojo> findCountryDDL() {
		return pojoUtil.transPo2Pojo(markerKindRepository.findCountryDDL());
	}
	
	public List<MarkerKindPojo> findCityDDL(String countryId) {
		return pojoUtil.transPo2Pojo(markerKindRepository.findByCountryIdIgnoreCase(countryId));
	}
}
