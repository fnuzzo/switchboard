package org.switchboard.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "DEVICE")
public class Device implements Serializable{

	private static final long serialVersionUID = -1122822586358762874L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "OS_VERSION")
	private String osVersion;

	@Column(name = "API_VERSION")
	private String apiVersion;
	
	@Column(name = "COUNT_MESSAGES")
	private Integer countmessage;
	
	public Integer getCountmessage() {
		return countmessage;
	}

	public void setCountmessage(Integer countmessage) {
		this.countmessage = countmessage;
	}

	@Column(name = "DEVICE_ID")
	private String device_id;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeviceId() {
		return device_id;
	}

	public void setDeviceId(String id) {
		this.device_id = id;
	}

	
	
/*	public String toString(){
		return new String("ID App: "+this.id+" App Name: "+this.name+" Email "+this.email);
	}*/
	
		
}