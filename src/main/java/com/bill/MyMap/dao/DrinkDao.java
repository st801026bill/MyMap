package com.bill.MyMap.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bill.MyMap.entity.DrinkPo;
import com.bill.MyMap.model.DrinkPojo;
import com.bill.MyMap.repository.DrinkRepository;
import com.bill.MyMap.util.PojoUtil;

@Service
public class DrinkDao {
	@Autowired
	DrinkRepository drinkrepository;
	@Autowired
	PojoUtil pojoUtil;
	
	public DrinkPojo findByName(String name) {
		DrinkPo po = drinkrepository.findByName(name);
		return pojoUtil.transPo2Pojo(po, "");
	}
}
