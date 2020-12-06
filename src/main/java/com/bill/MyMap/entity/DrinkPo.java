package com.bill.MyMap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "DRINKS_QUANTITY")
public class DrinkPo implements BasePo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SN", nullable = false)
	private Integer sn;
	
	@Column(name = "ID", length = 5, nullable = false)
	private String id;
	
	@Column(name = "NAME", length = 32, nullable = false)
	private String name;
	
	@Column(name = "QUANTITY", nullable = false)
	private String quantity;
}
