package com.bill.MyMap.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
public class HttpDataTransferObject implements BasePojo {
	
	@JsonProperty("DATA")
	private Map<String, ? extends Object> data;
	
	public HttpDataTransferObject() {
		super();
		this.data = new HashMap<>();
	}
}
