package org.switchboard.domain.config;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
 
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.switchboard.domain.Profile;




@Entity
@Table(name = "APPLE_CONFIG")
public class AppleConfig implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
    @Column(name="ID", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="profile"))
	private Integer id;


	 @OneToOne
	 @PrimaryKeyJoinColumn
	 private Profile profile;
	
	 
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

	
	
	
	
	// FOR iPhone
	@Column(name = "APNS_PASSWORD")
	private String apns_password;
	
	@Lob
	@Column(name="APNS_KEYSTORE", nullable=true, columnDefinition="mediumblob")
	private byte[] apns_keystore;
	
	
	
	public String getApns_password() {
		return apns_password;
	}

	public void setApns_password(String apns_password) {
		this.apns_password = apns_password;
	}

	public byte[] getApns_keystore() {
		return apns_keystore;
	}

	public void setApns_keystore(byte[] apns_keystore) {
		this.apns_keystore = apns_keystore;
	}
	
	
	
	
	
	
}
