package org.switchboard.domain.config;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name="ANDROID_CONFIG")
public class AndroidConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
    @Column(name="ID", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="profile"))
	private Integer id;


	 @OneToOne
	 @PrimaryKeyJoinColumn
	 private Profile profile;
	
		@Column(name = "GCM_KEY")
		private String gcm_key;

	 
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


	public String getGcm_key() {
		return gcm_key;
	}

	public void setGcm_key(String gcm_key) {
		this.gcm_key = gcm_key;
	}
	
	
	
	
}
