package org.switchboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.switchboard.domain.Profile;
import org.switchboard.domain.Device;






@Service("deviceService")
@Transactional
public class DeviceService {

	protected static Logger logger = Logger.getLogger("deviceService");

	@Resource(name="profileService")
	private ProfileService profileservice;
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;


	/**
	 * Retrieves all devices
	 */
	public List<Device> getAll(Integer profileId) {
		logger.debug("Retrieving all Device");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Profile as p LEFT JOIN FETCH  p.devices WHERE p.id="+profileId);

		Profile profile = (Profile) query.uniqueResult();

		// Retrieve all
		return  new ArrayList<Device>(profile.getDevices());
	}

	/**
	 * Retrieves all device
	 */
	public List<Device> getAll() {
		logger.debug("Retrieving all device");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Device");

		// Retrieve all
		return  query.list();
	}
	
	
	public String request(String json){
		
		String result = null;
		
		JSONObject obj = null;
		try {	
				
			obj = (JSONObject)new JSONParser().parse(json);
			
			logger.debug(obj.get("DEVICEKEY").toString());
		
			Integer profileId = profileservice.tokenExist(obj.get("DEVICEKEY").toString());
		
			if(profileId != null && obj.get("DEVICE_ID").toString() != null ){
			
				if(obj.get("ACTION").toString().equals("REGISTER")){
					
						registerDevice(profileId, obj);
						result = "registred";
				}

			}else{
				result= "no profile associated";	
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			result= "error parse json";
		}	
			
			return result;
	}
	

	public void registerDevice(Integer profileId, JSONObject obj){

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		logger.debug(obj.get("TYPE").toString());
		logger.debug(obj.get("DEVICE_ID").toString());
		logger.debug(obj.get("OS_VERSION").toString());
		logger.debug(obj.get("API_VERSION").toString());
		
		
		Device device = new Device();
		
		device.setType(obj.get("TYPE").toString());
		device.setDeviceId(obj.get("DEVICE_ID").toString());
		device.setOsVersion(obj.get("OS_VERSION").toString());
		device.setApiVersion(obj.get("API_VERSION").toString());
		session.save(device);

		// Retrieve existing profile via id
		Profile existingProfile = (Profile) session.get(Profile.class, profileId);

		// Assign updated values to this profile
		existingProfile.getDevices().add(device);

		// Save updates
		session.save(existingProfile);
		
	}
	
	
	public void unregisterDevice(JSONObject obj){
	
		logger.debug("Unregister Device: ");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
	
		
		String senderId = obj.get("DEVICE_ID").toString();
		
		Query query = session.createQuery("FROM Device as d WHERE d.sender_id LIKE"+senderId);
		Device device =  (Device) query.uniqueResult();
		
		this.delete(device.getId());
	}
	
	
	public void unregisterDevicebyId(String senderId){

		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("FROM Device as d WHERE d.sender_id LIKE"+senderId);
		Device device =  (Device) query.uniqueResult();
		
		this.delete(device.getId());
	}
	
	
	

	/**
	 * Adds a new device
	 */
	public void add(Integer profileId, Device device) {
		logger.debug("Adding new device");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Persists to db
		session.save(device);

		// Add to profile as well
		// Retrieve existing profile via id
		Profile existingApp = (Profile) session.get(Profile.class, profileId);

		// Assign updated values to this profile
		existingApp.getDevices().add(device);

		// Save updates
		session.save(existingApp);
	}




	/**
	 * Retrieves a single device
	 */
	public Device get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing device
		Device device = (Device) session.get(Device.class, id);

		// Persists to db
		return device;
	}




	/**
	 * Deletes an existing credit card
	 */
	public void delete(Integer id) {
		logger.debug("Deleting existing credit card");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Delete reference to foreign key credit card first
		// We need a SQL query instead of HQL query here to access the third table
		Query query = session.createSQLQuery("DELETE FROM PROFILE_DEVICE " + "WHERE DEVICE_ID="+id);
		query.executeUpdate();
		
		Device device =  (Device) session.get(Device.class, id);
		
		session.delete(device);
	}

	
	/**
	 * Edits an existing Device
	 */
	public void edit(Device device) {
		logger.debug("Editing existing device");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing credit card via id
		Device existingDevice = (Device) session.get(Device.class, device.getId());

		// Assign updated values to this credit card
		//  existingDevice.setType(device.getType());

		// Save updates
		session.save(existingDevice);
	}







}
