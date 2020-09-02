package com.bill.MyMap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bill.MyMap.entity.MarkerPo;
import com.bill.MyMap.model.MarkerPojo;
import com.bill.MyMap.repository.MarkerRepository;
import com.bill.MyMap.util.PojoUtil;

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
	
	/**
	 * <pre>
	 *  新增車險保單資料
	 * @param pojo
	 * @return
	 * </pre>
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addMarker(MarkerPojo pojo) {
		MarkerPo addedMarker = markerRepository.save(pojoUtil.transPojo2Po(pojo));
		//if(null == addedMarker.getSn()) throw new ModuleException(ErrorType.DATABASE_ERROR, "VehiclePolicy");
	}
}
