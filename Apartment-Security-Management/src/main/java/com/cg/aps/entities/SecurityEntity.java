package com.cg.aps.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="security_table")
public class SecurityEntity {
	@Id
	private String msgId;
	private String message;
	private String alert;

	public SecurityEntity() {
		
	}
	
	public SecurityEntity(String msgId,String message,String alert) {
		super();
		this.msgId=msgId;
		this.message=message;
		this.alert=alert;
	}
	
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}

	@Override
	public String toString() {
		return "SecurityEntity [msgId=" + msgId + ", message=" + message + ", alert=" + alert + "]";
	}
	
}