package org.switchboard.connector.gcm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.switchboard.domain.Device;
import org.switchboard.domain.Notification;
import org.switchboard.service.DeviceService;

import org.switchboard.connector.gcm.Constants;
import org.switchboard.connector.gcm.MulticastResult;
import org.switchboard.connector.gcm.Result;
import org.switchboard.connector.gcm.Message;
import org.switchboard.connector.gcm.Sender;




public class GcmConnector {

	 private static final int MULTICAST_SIZE = 1000;
	 
	 protected static Logger logger = Logger.getLogger("connector");
	 
	 private static final Executor threadPool = Executors.newFixedThreadPool(5);
	 
	Sender sender;
	
	@Resource(name="deviceService")
	DeviceService deviceservice;
	
	
	public String inizialize(String access_key) {
		
		sender = new Sender(access_key);
	
		return null;
	}



	public void sendPush(Notification not, List<String> devices) {
		try {
			
	    String status;
	    if (devices.isEmpty()) {
	      status = "Message ignored as there is no device registered!";
	    } else {
	      // NOTE: check below is for demonstration purposes; a real application
	      // could always send a multicast, even for just one recipient
	      if (devices.size() == 1) {
	        // send a single textsage using plain post
	        String registrationId = devices.get(0);
	        Message textsage = new Message.Builder()
	        .addData("key1", not.getMessage())
	        .build();
	        Result result;
		
				result = sender.send(textsage, registrationId, 5);
			
	        status = "Sent textsage to one device: " + result;
	      } else {
	        // send a multicast textsage using JSON
	        // must split in chunks of 1000 devices (GCM limit)
	        int total = devices.size();
	        List<String> partialDevices = new ArrayList<String>(total);
	        int counter = 0;
	        int tasks = 0;
	        for (String device : devices) {
	          counter++;
	          partialDevices.add(device);
	          int partialSize = partialDevices.size();
	          if (partialSize == MULTICAST_SIZE || counter == total) {
	            asyncSend(partialDevices,not.getMessage());
	            partialDevices.clear();
	            tasks++;
	          }
	        }
	        status = "Asynchronously sending " + tasks + " multicast textsages to " +
	            total + " devices";
	      }
	    }
	    
	    logger.debug("Result gdmSend: "+status.toString());
	    
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	  private void asyncSend(List<String> partialDevices, final String text) {
		    // make a copy
		    final List<String> devices = new ArrayList<String>(partialDevices);
		    
		    final List<String> devices_regId = new ArrayList<String>();
		    for (int i =0; i< devices.size(); i++ ) {
		    	devices_regId.add(devices.get(i));
		    }
		    
		    threadPool.execute(new Runnable() {

		      public void run() {
		        Message textsage = new Message.Builder()
		        .addData("key1", text)
		        .build();
		        
		        MulticastResult multicastResult;
		        try {
		          multicastResult = sender.send(textsage, devices_regId, 5);
		        } catch (IOException e) {
		          logger.error("Error posting textsages", e);
		          return;
		        }
		        List<Result> results = multicastResult.getResults();
		        // analyze the results
		        for (int i = 0; i < devices.size(); i++) {
		          String regId = devices_regId.get(i);
		          Result result = results.get(i);
		          String textsageId = result.getMessageId();
		          if (textsageId != null) {
		            logger.debug("Succesfully sent textsage to device: " + regId +
		                "; textsageId = " + textsageId);
		            String canonicalRegId = result.getCanonicalRegistrationId();
		            if (canonicalRegId != null) {
		             
// TO DO same device has more than on registration id: update it		            	
		              logger.info("canonicalRegId " + canonicalRegId);
		            }
		          } else {
		            String error = result.getErrorCodeName();
		            if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
		            		deviceservice.unregisterDevicebyId(regId);
		            } else {
		              logger.error("Error sending messagesage to " + regId + ": " + error);
		            }
		          }
		        }
		      }});
		  }
	
}

