package com.cg.aps.entities;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="visitortab")

public class VisitorEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(length=50)
	private String flatNo;  
	
	@Column(length=50)
	private String name;
	
	@Column(length=50)
	private String ownerName;
	@Column(name="dat",length=50)
	//@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private Date datepost;
	
	@Column(length=50)
	private String arrivalTime;
	
	@Column(length=50)
	private String departureTime;


	
	
	
	public VisitorEntity(String flatNo, String name, String ownerName, Date datepost, String arrivalTime,
			String departureTime) {
		super();
		this.flatNo = flatNo;
		this.name = name;
		this.ownerName = ownerName;
		this.datepost = datepost;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	@Override
	public String toString() {
		return "VisitorEntity [flatNo=" + flatNo + ", name=" + name + ", ownerName=" + ownerName + ", datepost="
				+ datepost + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + "]";
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Date getDatepost() {
		return datepost;
	}

	public void setDatepost(Date datepost) {
		this.datepost = datepost;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	
	public VisitorEntity() {
		
	}
}