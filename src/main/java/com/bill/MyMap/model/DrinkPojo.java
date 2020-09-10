package com.bill.MyMap.model;

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
public class DrinkPojo implements BasePojo {
	@JsonProperty(value = "SN")
	private Integer sn;
	@JsonProperty(value = "ID")
	private String id;
	@JsonProperty(value = "NAME")
	private String name;
	@JsonProperty(value = "QUANTITY")
	private String quantity;
}
