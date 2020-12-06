package com.bill.MyMap.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)		
public class MarkerPojo implements BasePojo {
	@JsonProperty(value = "SN")
	private Integer sn;
	@JsonProperty(value = "NAME")
	private String name;
	@JsonProperty(value = "COUNTRY_ID")
	private String countryId;
	@JsonProperty(value = "COUNTRY_NAME")
	private String countryName;
	@JsonProperty(value = "CITY_ID")
	private String cityId;
	@JsonProperty(value = "CITY_NAME")
	private String cityName;
	@JsonProperty(value = "ADDRESS")
	private String address;
	@JsonProperty(value = "LONGITUDE")
	private BigDecimal longitude;
	@JsonProperty(value = "LATITUDE")
	private BigDecimal latitude;
	@JsonProperty(value = "COMMENT")
	private String comment;
	@JsonProperty(value = "URL")
	private String url;
}
