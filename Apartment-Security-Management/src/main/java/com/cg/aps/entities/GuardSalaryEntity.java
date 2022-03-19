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
@Table(name="guardSalary2")
public class GuardSalaryEntity
 {
	@Id
	private long userId;
	@Column(length=50)
	private String name;
	@Column
	private long amount;
	@Column
	private String status;
	@Column(name="date1",length=50)
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
	private LocalDate date;
	
//	@OneToOne(mappedBy="guardSalaryEntity", targetEntity =GuardShiftEntity.class, orphanRemoval = true)
//	private GuardShiftEntity guardShiftEntity;
//
//	public GuardShiftEntity getGuardShiftEntity() {
//		return guardShiftEntity;
//	}
//
//	public void setGuardShiftEntity(GuardShiftEntity guardShiftEntity) {
//		this.guardShiftEntity = guardShiftEntity;
//	}

	GuardSalaryEntity()
	{
		
	}
	
	public GuardSalaryEntity(String name, long userId, long amount, String status,LocalDate date) {
		super();
		this.name = name;
		this.userId = userId;
		this.amount = amount;
		this.status = status;
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	

}