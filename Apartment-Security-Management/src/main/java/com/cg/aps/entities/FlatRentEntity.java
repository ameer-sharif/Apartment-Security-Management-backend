package com.cg.aps.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "flatrent_table")

public class FlatRentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer userId;

	@Column(name = "ownerName", length = 50)
	private String ownerName;

	@Column(name = "flatNo", length = 50)
	private String flatNo;

	@Column(name = "amount", length = 50)
	private String amount;

	@Column(name = "flatType", length = 50)
	private String flatType;

	//@JsonIgnore
	//@JsonProperty(access = Access.READ_ONLY)
//	@OneToOne(mappedBy="flatRent", targetEntity = FlatEntity.class, fetch = FetchType.LAZY, orphanRemoval = true)
//	private FlatEntity flat;
//
//	public FlatEntity getFlat() {
//		return flat;
//	}
//
//	public void setFlat(FlatEntity flat) {
//		this.flat = flat;
//	}
//
//	public FlatRentEntity() {
//
//	}

	public Integer getUserId() {
		return userId;
	}

	

	public FlatRentEntity(Integer userId, String ownerName, String flatNo, String amount) {
		super();
		this.userId = userId;
		this.ownerName = ownerName;
		this.flatNo = flatNo;
		this.amount = amount;
		
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFlatType() {
		return flatType;
	}

	public void setFlatType(String flatType) {
		this.flatType = flatType;
	}

	

}
