package com.bill.MyMap.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bill.MyMap.entity.MarkerKindPo;

@Repository
public interface MarkerKindRepository extends JpaRepository<MarkerKindPo, Integer> {
	public MarkerKindPo findBySn(Integer sn);
	public List<MarkerKindPo> findAll();
	
	@Query(value = "select min(SN) as SN, COUNTRY_ID, COUNTRY_NAME, '' as CITY_ID, '' as CITY_NAME from MARKER_KIND group by COUNTRY_ID, COUNTRY_NAME", nativeQuery = true)
	public List<MarkerKindPo> findCountryDDL();
	public List<MarkerKindPo> findByCountryIdIgnoreCase(String countryId);
}
