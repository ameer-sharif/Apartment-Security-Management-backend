package com.cg.aps.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="vehicle")
public class VehicleEntity {
	
	@Id
	private String name;
	
	@Column(name="date1",length=50)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;
	
	@Column(name="vehicleNo", length=50)
	private String vehicleNo;
	
	@Column(name="vehicleType", length=50)
	private String vehicleType;
	
	@Column(name="departureTime", length=50)
	private String departureTime;

	@Column(name="arrivalTime", length=50)
	private String arrivalTime;
	
	@Column(name="parkingTime", length=50)
	private String parkingTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getParkingTime() {
		return parkingTime;
	}

	public void setParkingTime(String parkingTime) {
		this.parkingTime = parkingTime;
	}

	@Override
	public String toString() {
		return "VehicleEntity [name=" + name + ", date=" + date + ", vehicleNo=" + vehicleNo + ", vehicleType="
				+ vehicleType + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", parkingTime="
				+ parkingTime + "]";
	}

	public VehicleEntity(String name, LocalDate date, String vehicleNo, String vehicleType, String departureTime,
			String arrivalTime, String parkingTime) {
		super();
		this.name = name;
		this.date = date;
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.parkingTime = parkingTime;
	}
	
	public VehicleEntity() {
		
	}
}