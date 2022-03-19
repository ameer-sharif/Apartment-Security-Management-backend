package com.cg.aps.entities;

import java.io.Serializable;
import java.time.LocalDate;

//import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "delivery")
public class DeliveryEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int deliveryId;
	@Column(length = 50)
	private String ownerName;
	@Column(name = "time_tb", length = 50)
	private String time1;
	@Column(name = "dat", length = 50)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;
	@Column(name = "status", length = 50)
	private String status1;

//	@JsonProperty(access = Access.READ_ONLY)
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "userId")
//	private FlatEntity flat;

	public DeliveryEntity() {

	}
	

	public DeliveryEntity(int deliveryId, String ownerName, String time1, LocalDate date, String status1) {
		super();
		this.deliveryId = deliveryId;
		this.ownerName = ownerName;
		this.time1 = time1;
		this.date = date;
		this.status1 = status1;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DeliveryEntity [deliveryId=" + deliveryId + ", ownerName=" + ownerName + ", time1=" + time1 + ", date="
				+ date + ", status1=" + status1 + "]";
	}

	

}