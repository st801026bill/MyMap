package com.bill.MyMap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bill.MyMap.entity.MarkerKindPo;
import com.bill.MyMap.entity.MarkerPo;

@Repository
public interface MarkerKindRepository extends JpaRepository<MarkerKindPo, Integer> {
	public MarkerKindPo findBySn(Integer sn);
	public List<MarkerKindPo> findAll();
}
