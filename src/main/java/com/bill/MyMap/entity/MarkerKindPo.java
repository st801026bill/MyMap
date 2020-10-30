package com.bill.MyMap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Entity
@Table(name = "MARKER_KIND")
public class MarkerKindPo implements BasePo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SN", nullable = false)
	private Integer sn;
	@Column(name = "COUNTRY_ID", 	length = 10, nullable = false)
	private String countryId;
	@Column(name = "COUNTRY_NAME", 	length = 64, nullable = true)
	private String countryName;
	@Column(name = "CITY_ID", 		length = 10, nullable = false)
	private String cityId;
	@Column(name = "CITY_NAME", 	length = 64, nullable = true)
	private String cityName;
}
