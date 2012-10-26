package org.switchboard.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.switchboard.connector.apns.ApnsConnector;
import org.switchboard.connector.gcm.GcmConnector;
import org.switchboard.connector.rmi.RmiConnector;
import org.switchboard.domain.Notification;
import org.switchboard.domain.Profile;
import org.switchboard.domain.Device;
import org.switchboard.domain.config.AndroidConfig;
import org.switchboard.domain.config.AppleConfig;
import org.switchboard.domain.config.BlackBerryConfig;

/**
 * Service for processing Profiles
 * 
 */

@Service("profileService")
@Transactional
public class ProfileService  {



	protected static Logger logger = Logger.getLogger("service");



	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;


	public String inizializeKey(int length){	
		Random rnd = new Random ();
		char[] arr = new char[length];

		for (int i=0; i<length; i++) {
			int n = rnd.nextInt (36);
			arr[i] = (char) (n < 10 ? '0'+n : 'a'+n-10);
		}

		return new String (arr);
	}

	public List<Profile> getAll() {

		logger.debug("Retrieving all Registred profile");


		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Profile");

		// Retrieve all
		return  query.list();
	}
	/**
	 * Retrieves a single person
	 */
	public Profile get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing person
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Profile as p LEFT JOIN FETCH  p.devices WHERE p.id="+id);

		return (Profile)query.uniqueResult();
	}




	public Integer tokenExist( String token ) {

		Integer profileId = null;

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("FROM Profile");
		List<Profile> profiles = query.list();
		for(Profile profile:profiles){
			if(profile.getDevice_key().equals(token)){
				logger.debug("token: "+token+" exist");
				profileId = profile.getId();	
				break;
			}else 
				logger.debug("token: "+token+" NOT exist");
		}
		return profileId;
	}

	/**
	 * Adds a new Profile
	 */
	public void add(Profile profile) {
		logger.debug("Adding new profile");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		profile.setClient_key( this.inizializeKey(16) );
		profile.setDevice_key( this.inizializeKey(12) );

		// Persists to db
		session.save(profile);

	}


	/**
	 * Deletes an existing profile
	 * @param id the id of the existing profile
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing profile");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		//Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Profile as a LEFT JOIN FETCH  a.devices WHERE a.id="+id);

		// Retrieve record
		Profile profile = (Profile) query.uniqueResult();
		Set<Device>  devices = profile.getDevices();

		// Delete 
		session.delete(profile);	  
		// Delete associated devices
		for (Device device: devices) {
			session.delete(device);
		}


	}


	/**
	 * Edits an existing profile
	 */
	public  void edit(Profile profile) {
		logger.debug("Editing existing profile");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();


		// Retrieve existing profile via id
		Profile existingProfile = (Profile) session.get(Profile.class, profile.getId());

		// Assign updated values to this credit card
		existingProfile.setName(profile.getName());
		existingProfile.setEmail(profile.getEmail());
		existingProfile.setDevice_key(profile.getDevice_key());
		existingProfile.setClient_key(profile.getClient_key());

		session.save(existingProfile);
	}

	public  void editAndroidConfig(Profile profile, AndroidConfig config) {
		logger.debug("Change Android Configuration");

		Session session = sessionFactory.getCurrentSession();
		// Configuration configuration; 

		Profile existingProfile = (Profile) session.get(Profile.class, profile.getId());
		logger.debug("existingProfile: "+existingProfile.getName());

		if( existingProfile.getAndconfig() == null) {

			AndroidConfig andconf = new AndroidConfig();
			andconf.setGcm_key(config.getGcm_key());

			existingProfile.setAndconfig(andconf);
			andconf.setProfile(existingProfile);

		} else {

			AndroidConfig andconf = existingProfile.getAndconfig();
			andconf.setGcm_key(config.getGcm_key());

			existingProfile.setAndconfig(andconf);
			andconf.setProfile(existingProfile);


		}

		session.save(existingProfile);	

	}

	public  void editBlackBerryConfig( Profile profile, BlackBerryConfig config) {
		logger.debug("Change BlackBerry Configuration");

		Session session = sessionFactory.getCurrentSession();

		Profile existingProfile = (Profile) session.get(Profile.class, profile.getId());

		if( existingProfile.getBBconfig() == null) {

			BlackBerryConfig bbconf = new BlackBerryConfig();
			bbconf.setRmi_url(config.getRmi_url());
			bbconf.setRmi_key(config.getRmi_key());
			bbconf.setRmi_password(config.getRmi_password());

			existingProfile.setBBconfig(bbconf);
			bbconf.setProfile(existingProfile);

		} else {

			BlackBerryConfig bbconf = existingProfile.getBBconfig();
			bbconf.setRmi_url(config.getRmi_url());
			bbconf.setRmi_key(config.getRmi_key());
			bbconf.setRmi_password(config.getRmi_password()); 

			existingProfile.setBBconfig(bbconf);
			bbconf.setProfile(existingProfile);


		}

		session.save(existingProfile);
	}

	public void editiPhoneConfig(AppleConfig config, Profile profile) {

		logger.debug("Change iPhone Configuration");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Profile existingProfile = (Profile) session.get(Profile.class, profile.getId());

		if( existingProfile.getAppleconfig() == null) {

			AppleConfig appleconf = new AppleConfig();
			appleconf.setApns_password(config.getApns_password());
			if(config.getApns_keystore() != null)
				appleconf.setApns_keystore(config.getApns_keystore());

			existingProfile.setAppleconfig(appleconf);
			appleconf.setProfile(existingProfile);

		} else {

			AppleConfig appleconf =   existingProfile.getAppleconfig();
			appleconf.setApns_password(config.getApns_password());
			if(config.getApns_keystore() != null)
				appleconf.setApns_keystore(config.getApns_keystore());

			existingProfile.setAppleconfig(appleconf);
			appleconf.setProfile(existingProfile);


		}

	}





	public void sendMessages(Profile profile, Notification not) {

		GcmConnector gcmconn = new GcmConnector();
		RmiConnector rmiconn = new RmiConnector();
		ApnsConnector apnsconn = new ApnsConnector();


		List<String> andDevice = new ArrayList<String>();
		List<String> blackBerryDevice = new ArrayList<String>();
		List<String> appleDevice = new ArrayList<String>();

		logger.debug("Num Device: "+profile.getDevices().size()+"  message: "+not.getMessage());



		for(Device device: profile.getDevices()){	

			if( device.getType().equals( "ANDROID" ) ) {

				andDevice.add( device.getDeviceId() );
			}
			else if( device.getType().equals( "BLACKBERRY" ) ) {

				blackBerryDevice.add(device.getDeviceId());
			}
			else 	if( device.getType().equals("APPLE") ) {

				appleDevice.add( device.getDeviceId() );
			}
		}


		if( !andDevice.isEmpty() ) {
			gcmconn.inizialize(profile.getAndconfig().getGcm_key());
			gcmconn.sendPush(not, andDevice);

		}


		//TEST CASE 
		//blackBerryDevice.add("23A29EA4");


		if( !blackBerryDevice.isEmpty() ) {
			rmiconn.inizialize(profile.getBBconfig().getRmi_key(), profile.getBBconfig().getRmi_url(), profile.getBBconfig().getRmi_password(), not, blackBerryDevice);

		}

		appleDevice.add("9e160351eab7648038e12d832fc4c9b90fbe07b2");
		if( !appleDevice.isEmpty() ) {		
			apnsconn.inizialize(profile.getAppleconfig().getApns_keystore(), profile.getAppleconfig().getApns_password(), not, appleDevice);	
		}





	}






}
