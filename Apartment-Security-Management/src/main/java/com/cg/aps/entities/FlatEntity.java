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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "flat_table")
public class FlatEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer userId;
	// @NotBlank(message="ownerName should not be empty")

	@Column(name = "ownerName", length = 50)
	private String ownerName;

	@Column(name = "flatNo", length = 50)
	private String flatNo;

	@Column(name = "floorNo", length = 50)
	private String floorNo;

	@Column(name = "flatType", length = 50)
	private String flatType;

	
	// MAPPING FlatEntity BY FlatRentEntity
//	@JsonProperty(access = Access.READ_ONLY)
//	@JsonIgnore
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = FlatRentEntity.class, orphanRemoval = true)
//	@JoinColumn(name = "userId")
//	private FlatRentEntity flatRent;

	
//
//	// MAPPPING FlatEntity BY VisitorEntity
//	@JsonProperty(access = Access.READ_ONLY)
//	@JsonIgnore
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flat")
//	private List<VisitorEntity> visitor;



//	public FlatRentEntity getFlatRent() {
//		return flatRent;
//	}
//
//	public void setFlatRent(FlatRentEntity flatRent) {
//		this.flatRent = flatRent;
//	}

//	
//	public List<VisitorEntity> getVisitor() {
//		return visitor;
//	}
//
//	public void setVisitor(List<VisitorEntity> visitor) {
//		this.visitor = visitor;
//	}
//
//	public List<DomesticHelpEntity> getDomestic() {
//		return domestic;
//	}
//
//	public void setDomestic(List<DomesticHelpEntity> domestic) {
//		this.domestic = domestic;
//	}

	public FlatEntity() {

	}
	

	

	public FlatEntity(Integer userId, String ownerName, String flatNo, String floorNo, String flatType) {
		super();
		this.userId = userId;
		this.ownerName = ownerName;
		this.flatNo = flatNo;
		this.floorNo = floorNo;
		this.flatType = flatType;
	}




	public Integer getUserId() {
		return userId;
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

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getFlatType() {
		return flatType;
	}

	public void setFlatType(String flatType) {
		this.flatType = flatType;
	}




	

	

}
