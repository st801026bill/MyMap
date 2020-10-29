package com.bill.MyMap.model;

import java.math.BigInteger;
import java.util.Date;

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
	@JsonProperty(value = "KIND")
	private String kind;
	@JsonProperty(value = "ADDRESS")
	private String address;
	@JsonProperty(value = "LONGITUDE")
	private float longitude;
	@JsonProperty(value = "LATITUDE")
	private float latitude;
	@JsonProperty(value = "COMMENT")
	private String comment;
	@JsonProperty(value = "URL")
	private String url;
}
