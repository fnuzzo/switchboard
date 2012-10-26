package org.switchboard.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.switchboard.connector.apns.ApnsConnector;
import org.switchboard.connector.gcm.GcmConnector;
import org.switchboard.connector.rmi.RmiConnector;
import org.switchboard.domain.Device;
import org.switchboard.domain.Notification;
import org.switchboard.domain.Profile;



@Service("clientService")
@Transactional
public class ClientService {
	
	 protected static Logger logger = Logger.getLogger("service");

	@Resource(name="profileService")
	private ProfileService profileservice;
		
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
			
	GcmConnector gcmconn;
	RmiConnector rmiconn;
	ApnsConnector apnsconn;

	List<String> andDevice;
	List<String> rimDevice;
	List<String> appleDevice;
	

	
	public void sendAndoridPush(Profile profile, Notification not) {
		 
		 gcmconn = new GcmConnector();
		 andDevice = new ArrayList<String>();
		 
		 for( Device device: profile.getDevices() ) {	
				if( device.getType().equals( "ANDROID" ) )
							andDevice.add( device.getDeviceId() );
		 	}
			gcmconn.inizialize( profile.getAndconfig().getGcm_key() );
			gcmconn.sendPush( not, andDevice );
	 }
	 
	 public void sendRIMPush( Profile profile, Notification not ) {
		 
		 rmiconn = new RmiConnector();
		 rimDevice = new ArrayList<String>();
		 
		 for( Device device: profile.getDevices() ){	
				if( device.getType().equals( "BLACKBERRY" ) )
							rimDevice.add( device.getDeviceId() );
		 }
			//TEST CASE 
			rimDevice.add( "23A29EA4" );
			rmiconn.inizialize( profile.getBBconfig().getRmi_key(), profile.getBBconfig().getRmi_url(), profile.getBBconfig().getRmi_password(), not, rimDevice );
	 }

	 public void sendApplePush( Profile profile, Notification not ) {
		 
		 ApnsConnector apnsconn = new ApnsConnector();
		 appleDevice = new ArrayList<String>();
		 
		 for( Device device: profile.getDevices() ) {	
				if( device.getType().equals( "APPLE" ) )
							appleDevice.add( device.getDeviceId() );
		 	}
			//TEST CASE 
			appleDevice.add("9e160351eab7648038e12d832fc4c9b90fbe07b2");
		 apnsconn.inizialize(profile.getAppleconfig().getApns_keystore(), profile.getAppleconfig().getApns_password(), not, appleDevice);	
	 }
	 

	 public void sendAllPush(Profile profile, Notification not) {
				 
			 gcmconn = new GcmConnector();
			 rmiconn = new RmiConnector();
			 apnsconn = new ApnsConnector();
			
			 andDevice = new ArrayList<String>();
			 rimDevice = new ArrayList<String>();
			 appleDevice = new ArrayList<String>();

			logger.debug("Num Device: "+profile.getDevices().size()+"  message: "+not.getMessage());



			for(Device device: profile.getDevices()){	
			
				if( device.getType().equals( "ANDROID" ) ) {
					
							andDevice.add( device.getDeviceId() );
				}
				else if( device.getType().equals( "BLACKBERRY" ) ) {
					
							rimDevice.add(device.getDeviceId());
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
			rimDevice.add("23A29EA4");
		
			if( !rimDevice.isEmpty() ) {
				rmiconn.inizialize(profile.getBBconfig().getRmi_key(), profile.getBBconfig().getRmi_url(), profile.getBBconfig().getRmi_password(), not, rimDevice);
						
			}
			//TEST CASE 
			appleDevice.add("9e160351eab7648038e12d832fc4c9b90fbe07b2");
			if( !appleDevice.isEmpty() ) {		
				apnsconn.inizialize(profile.getAppleconfig().getApns_keystore(), profile.getAppleconfig().getApns_password(), not, appleDevice);	
			}
				
		}
	 
	 
		public String request(String json){

			String result=null;
			
			JSONObject obj = null;

			try {	
					obj = (JSONObject)new JSONParser().parse(json);
	
					logger.debug(obj.get("CLIENTKEY").toString());
					logger.debug(obj.get("TIMETOLIVE").toString());
					logger.debug(obj.get("NOTITYPE").toString());
					logger.debug(obj.get("TEXT").toString());
					
					Integer profileId = tokenExist(obj.get("CLIENTKEY").toString());
			
			
				if(profileId != null) {
					
					Notification notific = new Notification();
	
						notific.setDevice_type( obj.get("DEVICETYPE").toString() );
						notific.setTimetolive( obj.get("TIMETOLIVE").toString());
						notific.setNotification_type( obj.get("NOTTYPE").toString());
						notific.setMessage( obj.get("TEXT").toString());	
				
						Session session = sessionFactory.getCurrentSession();
						Profile profile = (Profile) session.get(Profile.class, profileId);
					
						if ( profile.getDevices().isEmpty() ) {
							
							result = "no device registrated";
							
							} else {
								result = "accepted";
								if( notific.getDevice_type().equals("BLACKBERRY") )
									sendRIMPush(profile, notific);
								else if(notific.getDevice_type().equals("ANDROID"))
									sendAndoridPush(profile, notific);
								else if(notific.getDevice_type().equals("APPLE"))
									sendApplePush(profile, notific);
								else if(notific.getDevice_type().equals("ALL"))
									sendAllPush(profile, notific);
								else
									result= "no supported platform";

							}		
				} else {
					result = "no profile associated";
				}
			
					
			} catch (ParseException e) {
				result = "error parse ison";
			}
			catch (ClassCastException e) {
				result = "error parse json";
			}
		
				
				return result;		
		}
		

		
		public Integer tokenExist( String token ) {
			
			Integer profileId = null;
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("FROM Profile");
		List<Profile> profiles = query.list();
				for(Profile profile:profiles){
					if(profile.getClient_key().equals(token)){
						profileId = profile.getId();	
						break;
					}else 
						logger.debug("token: "+token+" NOT exist");
				}
			return profileId;
	}
		
		
		

	 
	 
	 
	 
	 
	 
}
