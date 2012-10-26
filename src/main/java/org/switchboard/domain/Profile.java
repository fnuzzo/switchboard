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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.switchboard.domain.config.AndroidConfig;
import org.switchboard.domain.config.AppleConfig;
import org.switchboard.domain.config.BlackBerryConfig;



@Entity
@Table(name = "PROFILE")
public class Profile implements Serializable{

	private static final long serialVersionUID = -1122822586358762874L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "DEVICE_KEY")
	private String device_key;
	
	@Column(name = "CLIENT_KEY")
	private String client_key;
	

	
	@OneToMany
	private Set<Device> devices;
	
	
    @OneToOne(mappedBy="profile", cascade=CascadeType.ALL)
	 private BlackBerryConfig bbconfig;

    @OneToOne(mappedBy="profile", cascade=CascadeType.ALL)
	 private AndroidConfig andconfig;
    
    @OneToOne(mappedBy="profile", cascade=CascadeType.ALL)
  	 private AppleConfig appleconfig;
    
	

	public BlackBerryConfig getBBconfig() {
		return bbconfig;
	}

	public void setBBconfig(BlackBerryConfig bbconfig) {
		this.bbconfig = bbconfig;
	}

	public AndroidConfig getAndconfig() {
		return andconfig;
	}

	public void setAndconfig(AndroidConfig andconfig) {
		this.andconfig = andconfig;
	}

	public AppleConfig getAppleconfig() {
		return appleconfig;
	}

	public void setAppleconfig(AppleConfig appleconfig) {
		this.appleconfig = appleconfig;
	}

	public String getClient_key() {
		return client_key;
	}

	public void setClient_key(String client_key) {
		this.client_key =client_key;
	}

	public String getDevice_key() {
		return device_key;
	}

	public void setDevice_key(String device_key) {
		this.device_key = device_key;
	}
	

	


	
		
	public Integer getId() {
		return id;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	 public Set<Device> getDevices() {
		  return devices;
		 }
		 
	public void setDevices(Set<Device> device) {
		  this.devices = devices;		 }

/*	public String toString(){
		return new String("ID App: "+this.id+" App Name: "+this.name+" Email "+this.email);
	}*/
	
		
}
