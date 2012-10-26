package org.switchboard.connector.apns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushedNotification;
import javapns.test.*;

import org.switchboard.domain.Notification;
import org.switchboard.service.DeviceService;

public class ApnsConnector {

	@Resource(name="deviceService")
	DeviceService deviceservice;
	
	
	
	public String inizialize(byte[] bs, String password, Notification message, List<String> appleDevice){
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("certificate.p12");
			fos.write(bs);
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 //Push.test(bs, password, false,"9e160351eab7648038e12d832fc4c9b90fbe07b2" );
			 Push.alert(message.getMessage(), bs, password, false, appleDevice);
		} catch (CommunicationException e) {
			
			e.printStackTrace();
		} catch (KeystoreException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}
