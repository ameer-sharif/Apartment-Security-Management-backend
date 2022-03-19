package com.cg.aps.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name="guardshift2")
public class GuardShiftEntity {
	@Id
	private long userId;
	@Column
	private String name;
	@Column
	private String time;
	@Column(name="date1",length=50)
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate date;
	
//	//@JsonIgnore
//	@OneToOne(mappedBy="guardShiftEntity", targetEntity = GuardTrainingEntity.class, orphanRemoval = true)
//	private GuardTrainingEntity guardTrainingEntity;
//	
//	@JsonProperty(access = Access.READ_ONLY)
//	@JsonIgnore
//	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH, targetEntity = GuardSalaryEntity.class, orphanRemoval = true)
//	@JoinColumn(name = "userId")
//	private GuardSalaryEntity guardSalaryEntity;
	
	
	
	
	
	

//	public GuardSalaryEntity getGuardSalaryEntity() {
//		return guardSalaryEntity;
//	}
//
//	public void setGuardSalaryEntity(GuardSalaryEntity guardSalaryEntity) {
//		this.guardSalaryEntity = guardSalaryEntity;
//	}

//	public GuardTrainingEntity getGuardTrainingEntity() {
//		return guardTrainingEntity;
//	}
//
//	public void setGuardTrainingEntity(GuardTrainingEntity guardTrainingEntity) {
//		this.guardTrainingEntity = guardTrainingEntity;
//	}

	
	public GuardShiftEntity(long userId, String name, String time,LocalDate date) {
		super();
		this.userId = userId;
		this.name = name;
		this.time = time;
		this.date = date;
	}
	
	public GuardShiftEntity()
	{
		
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

}