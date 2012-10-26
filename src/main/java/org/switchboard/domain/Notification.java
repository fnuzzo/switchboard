package org.switchboard.domain;

import java.io.Serializable;

public class Notification implements Serializable{

	String message;
	String device_type;
	String timetolive;
	String notification_type;

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getTimetolive() {
		return timetolive;
	}

	public void setTimetolive(String timetolive) {
		this.timetolive = timetolive;
	}

	public String getNotification_type() {
		return notification_type;
	}

	public void setNotification_type(String notification_type) {
		this.notification_type = notification_type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	


}
