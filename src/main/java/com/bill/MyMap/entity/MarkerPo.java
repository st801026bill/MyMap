package com.bill.MyMap.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Entity
@Table(name = "MARKER")
public class MarkerPo implements BasePo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SN", nullable = false)
	private Integer sn;
	
	@Column(name = "NAME", length = 32, nullable = false)
	private String name;
	@Column(name = "ADDRESS", length = 128, nullable = false)
	private String address;
	@JsonProperty(value = "LONGITUDE")
	private float longitude;
	@JsonProperty(value = "LATITUDE")
	private float latitude;
	@Column(name = "COMMENT", length = 128, nullable = false)
	private String comment;
	@Column(name = "URL", length = 512, nullable = false)
	private String url;
}
