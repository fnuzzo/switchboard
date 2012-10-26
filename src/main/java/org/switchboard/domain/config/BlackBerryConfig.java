package org.switchboard.domain.config;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.switchboard.domain.Profile;

@Entity
@Table(name = "BB_CONFIG")
public class BlackBerryConfig implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="ID", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="profile"))
	private Integer id;


	 @OneToOne
	 @PrimaryKeyJoinColumn
	 private Profile profile;
	

		@Column(name = "RMI_KEY")
		private String rmi_key;
		
		@Column(name = "RMI_PASSWORD")
		private String rmi_password;
		
		@Column(name = "RMI_URL")
		private String rmi_url;
		
		
		
		public Profile getProfile() {
			return profile;
		}



		public void setProfile(Profile profile) {
			this.profile = profile;
		}



		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

	

	public String getRmi_key() {
		return rmi_key;
	}

	public void setRmi_key(String rmi_key) {
		this.rmi_key = rmi_key;
	}
	
	public String getRmi_password() {
		return rmi_password;
	}

	public void setRmi_password(String rmi_password) {
		this.rmi_password = rmi_password;
	}

	public String getRmi_url() {
		return rmi_url;
	}

	public void setRmi_url(String rmi_url) {
		this.rmi_url = rmi_url;
	}
	

	
	
	
}
