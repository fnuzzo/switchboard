package org.switchboard.connector.rmi;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.switchboard.domain.Notification;

import net.rim.pushsdk.commons.IdGenerator;
import net.rim.pushsdk.commons.IdGeneratorImpl;
import net.rim.pushsdk.commons.PushSDKException;
import net.rim.pushsdk.commons.PushSDKProperties;
import net.rim.pushsdk.commons.UnauthorizedException;
import net.rim.pushsdk.commons.content.Content;
import net.rim.pushsdk.commons.content.TextContent;
import net.rim.pushsdk.commons.http.HttpClientImpl;
import net.rim.pushsdk.pap.PapService;
import net.rim.pushsdk.pap.PapServiceImpl;
import net.rim.pushsdk.pap.control.PushMessageControl;
import net.rim.pushsdk.pap.unmarshal.BadMessageException;
import net.rim.pushsdk.pap.unmarshal.PushResponse;



public class RmiConnector {

	 
	 protected static Logger logger = Logger.getLogger("connector");
	 
	// ResourceBundle properties;
	private PushSDKProperties properties;
	
	
private  String password; // = "xY28FAsn";
private  String targetURL; //= "https://cp3004.pushapi.eval.blackberry.com/mss/PD_pushRequest";
private  String APP_ID; //= "3004-6l049B12DD94Mr74f470m578ch7e0723901";

	public String inizialize(String app_id, String url, String password, Notification not, List<String> pins){
		
		this.APP_ID = app_id;
		this.password = password;
		this.targetURL = url;
		
		logger.info("APP_ID: "+app_id);
		logger.info("password: "+password);
		logger.info("URL: "+ url);
	
		properties = new RmiPropriertyImp();
		properties.setPublicPpgAddress(targetURL);

	
			List<String> pin = new ArrayList<String>();
			pin.add("23A29EA4");
			//List<String> pins = new ArrayList<String>();
			//logger.info("PIN : "+ pins.get(0).toString());
				IdGenerator idGenerator = new IdGeneratorImpl();
				
				PushMessageControl pushMessageControl = new PushMessageControl(true, idGenerator, APP_ID, pin);
				Content content = new TextContent(not.getMessage());
			
				
				PapService papService = new PapServiceImpl();
				
				
				//PushSDKProperties properties = getProperties();
				PushSDKProperties properties = new RmiPropriertyImp();
	
				HttpClientImpl client = new HttpClientImpl();
				client.setPushSDKProperties(properties);
				papService.setHttpClient(client);
				papService.setPushSDKProperties(properties);
				
			
				
				
				try{
					PushResponse response;
					try {
						response = papService.push(APP_ID, password, APP_ID, pushMessageControl, content);
						logger.info("RISPOSTA: "+ response.getDescription());
						System.out.println("response : "+response.getDescription());
					} catch (PushSDKException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadMessageException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnauthorizedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					}catch(NullPointerException ex){
							ex.printStackTrace();
					}

		
		
		
		
		
		
		
		
		
		return null;
		
	}

	public void sendPush(String message, List<String> pins ){
	
		try{
			List<String> pin = new ArrayList<String>();
			pins.add("23A29EA4");
			//List<String> pins = new ArrayList<String>();
			//logger.info("PIN : "+ pins.get(0).toString());
				IdGenerator idGenerator = new IdGeneratorImpl();
				
				PushMessageControl pushMessageControl = new PushMessageControl(true, idGenerator, APP_ID, pin);
				Content content = new TextContent("push text");
				
				PapService papService = new PapServiceImpl();
				
				
				//PushSDKProperties properties = getProperties();
				PushSDKProperties properties = new RmiPropriertyImp();
				

				
				
				
				HttpClientImpl client = new HttpClientImpl();
				client.setPushSDKProperties(properties);
				papService.setHttpClient(client);
				papService.setPushSDKProperties(properties);
				
			
				
				
				try{
					PushResponse response = papService.push(APP_ID, password, APP_ID, pushMessageControl, content);
					System.out.println("response : "+response.getDescription());
				
					}catch(NullPointerException ex){
							ex.printStackTrace();
					}

		} catch (Exception e) {
				e.printStackTrace();
		}
}
		
	}


