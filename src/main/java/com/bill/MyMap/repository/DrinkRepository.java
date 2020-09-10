package com.bill.MyMap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bill.MyMap.entity.DrinkPo;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkPo, Integer> {
	public DrinkPo findByName(String name);
}
