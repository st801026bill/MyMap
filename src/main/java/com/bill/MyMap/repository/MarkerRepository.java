package com.bill.MyMap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bill.MyMap.entity.MarkerPo;

@Repository
public interface MarkerRepository extends JpaRepository<MarkerPo, Integer> {
	public MarkerPo findBySn(Integer sn);	
}
