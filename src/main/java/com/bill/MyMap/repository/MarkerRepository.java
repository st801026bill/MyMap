package com.bill.MyMap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bill.MyMap.model.Marker;

@Repository
public interface MarkerRepository extends JpaRepository<Marker, Integer> {
	public Marker findBySn(Integer sn);
}
