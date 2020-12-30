package com.bill.MyMap.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bill.MyMap.entity.MarkerPo;
import com.bill.MyMap.enums.ErrorType;
import com.bill.MyMap.exception.ModuleException;
import com.bill.MyMap.model.MarkerPojo;
import com.bill.MyMap.repository.MarkerRepository;
import com.bill.MyMap.util.PojoUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarkerDao {
	@Autowired
	MarkerRepository markerRepository;
	@Autowired
	PojoUtil pojoUtil;
	
	public MarkerPojo findBySn(Integer sn) {
		MarkerPo po = markerRepository.findBySn(sn);
		return pojoUtil.transPo2Pojo(po, "");
	}
	
	public List<MarkerPojo> findByCountryId(String countryId) {
		List<MarkerPo> pos = markerRepository.findByCountryIdIgnoreCase(countryId);
		return pojoUtil.transPo2Pojo(pos, "");
	}
	
	public List<MarkerPojo> findByCountryIdAndCityId(String countryId, String cityId) {
		List<MarkerPo> po = markerRepository.findByCountryIdIgnoreCaseAndCityIdIgnoreCase(countryId, cityId);
		return pojoUtil.transPo2Pojo(po, "");
	}

	public List<MarkerPojo> findAll() {
		return pojoUtil.transPo2Pojo(markerRepository.findAll());
	}
	
	/**
	 * <pre>
	 *  新增/修改 點位資料
	 * @param pojo
	 * @return
	 * </pre>
	 */
	@Transactional(rollbackFor = Exception.class)
	public MarkerPojo saveMarker(MarkerPojo pojo) {
		MarkerPo po = markerRepository.save(pojoUtil.transPojo2Po(pojo));
		if(null == po.getSn()) throw new ModuleException(ErrorType.DATABASE_ERROR, "Marker");
		return pojoUtil.transPo2Pojo(po, "");
	}
	
	/**
	 * <pre>
	 *  刪除 點位資料
	 * @param pojo
	 * @return
	 * </pre>
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteMarkerBySn(Integer sn) {
		try {
			markerRepository.deleteById(sn);
		} catch (Exception e) {
			log.error("delete fails... "+ e.getMessage());
		}
	}
}
