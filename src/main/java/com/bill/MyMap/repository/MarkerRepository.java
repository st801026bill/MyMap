package com.bill.MyMap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bill.MyMap.entity.MarkerPo;

@Repository
public interface MarkerRepository extends JpaRepository<MarkerPo, Integer> {
	public MarkerPo findBySn(Integer sn);
	public List<MarkerPo> findByCountryIdIgnoreCase(String countryId);
	public List<MarkerPo> findByCountryIdIgnoreCaseAndCityIdIgnoreCase(String countryId, String cityId);
	public List<MarkerPo> findAll();
}
