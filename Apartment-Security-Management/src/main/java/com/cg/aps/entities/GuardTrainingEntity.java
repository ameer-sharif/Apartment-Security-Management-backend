package com.cg.aps.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;




//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name="guardtrain21")
public class GuardTrainingEntity {

	@Id
	private long userId;
	@Column(length=50)
	private String name;
	@Column
	private String mobileNo;
	@Column(name="status1",length=50)
	private String status;
	@Column
	private String timeing;
	@Column(name="date1",length=50)
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate date;
	
//	@JsonProperty(access = Access.READ_ONLY)
//	@JsonIgnore
//	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH, targetEntity = GuardShiftEntity.class, orphanRemoval = true)
//	@JoinColumn(name = "userId")
//	private GuardShiftEntity guardShiftEntity;
	

	

//	public GuardShiftEntity getGuardShiftEntity() {
//		return guardShiftEntity;
//	}
//
//
//	public void setGuardShiftEntity(GuardShiftEntity guardShiftEntity) {
//		this.guardShiftEntity = guardShiftEntity;
//	}


	public GuardTrainingEntity()
	{
		
	}
	
	
	public GuardTrainingEntity(long userId, String name, String mobileNo, String status, String timeing,
			LocalDate date) {
		super();
		this.userId = userId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.status = status;
		this.timeing = timeing;
		this.date = date;
	}




	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeing() {
		return timeing;
	}

	public void setTimeing(String timeing) {
		this.timeing = timeing;
	}
	
	


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "GuardTrainingEntity [userId=" + userId + ", name=" + name + ", mobileNo=" + mobileNo + ", status1="
				+ status + ", timeing=" + timeing + ", date1=" + date + "]";
	}
	
	


}
