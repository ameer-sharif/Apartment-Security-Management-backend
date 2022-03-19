package com.cg.aps.entities;

//import java.sql.Time;
//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Lob;
import javax.persistence.Table;
//import javax.persistence.Temporal;


@Entity
@Table (name="domestic_help")
public class DomesticHelpEntity {
	@Id
	@Column(name="flatnum")
	private String flatNo;
	//@Lob
	@Column(name="ownername")
	private String ownerName;
	//@Lob
	@Column(name="name")
	private String name;
	//@Lob
	@Column(name="helptype")
	private String helpType;
	//@Lob
	//@Temporal(value=TemporalType.DATE)
	@Column(name="arrtime")
	private String arrivalTime;
	//private java.sql.Time arrivalTime;
	//@Lob
	@Column(name="deptime")
	private String departureTime;
	//private java.sql.Time departureTime;
	//@Lob
	@Column(name="dat")
	private String dat;
	//private java.sql.Date dat;
	
	public DomesticHelpEntity() {
		
	}
	
	public DomesticHelpEntity(String flatNo,String ownerName,String name,String helpType,String arrivalTime,String departureTime,String dat) {
		super();
		this.flatNo=flatNo;
		this.ownerName=ownerName;
		this.name=name;
		this.helpType=helpType;
		this.arrivalTime=arrivalTime;
		this.departureTime=departureTime;
		this.dat=dat;
	}
	
	public String getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHelpType() {
		return helpType;
	}
	public void setHelpType(String helpType) {
		this.helpType = helpType;
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

	public String getDat() {
		return dat;
	}

	public void setDat(String dat) {
		this.dat = dat;
	}

	@Override
	public String toString() {
		return "DomesticHelpEntity [flatNo=" + flatNo + ", ownerName=" + ownerName + ", name=" + name + ", helpType="
				+ helpType + ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", date=" + dat
				+ "]";
	}
	
}