package org.switchboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.switchboard.domain.Profile;
import org.switchboard.domain.Device;
import org.switchboard.domain.Notification;
import org.switchboard.domain.config.AndroidConfig;
import org.switchboard.domain.config.AppleConfig;
import org.switchboard.domain.config.BlackBerryConfig;
import org.switchboard.service.ConfigurationService;
import org.switchboard.service.ProfileService;
import org.switchboard.service.DeviceService;


@Controller
@RequestMapping("/main")
public class ProfileController {
	
	
	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="profileService")
	private ProfileService profileservice;
	
	@Resource(name="deviceService")
	private DeviceService deviceservice;
	
	@Resource(name="configService")
	private ConfigurationService configservice;
	

	
	  @RequestMapping(value = "/profiles", method = RequestMethod.GET)
	  public String getProfiles(Model model) {
	    	logger.debug("Received request to show all Profile");
	
	    	// Retrieve all profiles by delegating the call to profileService
	    	List<Profile> profiles = profileservice.getAll();

	        
	    	// Attach profiles to the Model
	    	model.addAttribute("profiles", profiles);
	    	
	    	return "profilespage";
		}
	
	
	  	@RequestMapping(value = "/profiles/add", method = RequestMethod.GET)
	    public String getAdd( Model model ) {
	    	logger.debug("Received request to show add Profile");
	    
	    	// Create new Profile and add to model
	    	model.addAttribute( "profileAttribute" , new Profile() );
	  
	    	return "addpage";
		}
	

	    @RequestMapping(value = "/profiles/add", method = RequestMethod.POST)
	    public String add( @ModelAttribute( "profileAttribute" ) Profile profile, Model model) {
			logger.debug("Received request to add new Profile");
			
			// Call profileService to do the actual adding
			profileservice.add(profile);
			
			// Add id reference to Model
			model.addAttribute("device_key", profile.getDevice_key());
			model.addAttribute("client_key", profile.getClient_key());

			return "addedpage";
		}
	
	    @RequestMapping(value = "/profiles/delete", method = RequestMethod.GET)
	    public String delete(@RequestParam(value="id", required=true) Integer id, Model model) {
			logger.debug("Received request to delete existing Profile");
			
			// Call profileService to do the actual deleting
			profileservice.delete(id);
			
			return "redirect:/switchboard/profiles";
		}

	    @RequestMapping(value = "/profiles/edit", method = RequestMethod.GET)
	    public String getEdit(@RequestParam(value="id", required=true) Integer id,  Model model) {
	    	logger.debug("Received request to show edit page");
	    	
	    	logger.debug("key "+ profileservice.get(id).getDevice_key()+" ClientKey "+ profileservice.get(id).getClient_key() );
	    	
	    	model.addAttribute("profileAttribute", profileservice.get(id));
	    	
	    	
	    	return "editpage";
		}

	    @RequestMapping(value = "/profiles/edit", method = RequestMethod.POST)
	    public String saveEdit(@ModelAttribute("profileAttribute") Profile profile, @RequestParam(value="id", required=true) Integer id) {
	    	logger.debug("Received request to update profile");
	    	
	    	// We manually assign the id because we disabled it in the JSP page
	    	// When a field is disabled it will not be included in the ModelAttribute
	    	profile.setId(id);
	    	
	    	profileservice.edit(profile);

			return "redirect:/switchboard/profiles";
		}
	    
	    

/*	    @RequestMapping(value = "/profiles/edit/android", method = RequestMethod.GET)
	    public String getEditAndroidConf(@RequestParam(value="id", required=true) Integer id,  Model model) {
	    	logger.debug("Received request to show Android edit page");
	  
	    	model.addAttribute("profileAttribute", profileservice.get(id));
	   
	    	return "androidconf";
		}
	    
	    @RequestMapping(value = "/profiles/edit/android", method = RequestMethod.POST)
	    public String saveAndroidConf(@ModelAttribute("profileAttribute") Profile profile, @RequestParam(value="id", required=true) Integer id) {
	    	logger.debug("Received request to update Android Config");
	    	
	    	// We manually assign the id because we disabled it in the JSP page
	    	// When a field is disabled it will not be included in the ModelAttribute
	    	profile.setId(id);
	    	
	    	profileservice.editAndroidConfig(profile);

			return "redirect:/switchboard/profiles";
		}*/
	    
	    @RequestMapping(value = "/profiles/edit/android", method = RequestMethod.GET)
	    public String getEditAndroidConf(@RequestParam(value="id", required=true) Integer id,  Model model) {
	    	logger.debug("Received request to show Android edit page");

	    	AndroidConfig andConfig;
	    	
	    	if(  profileservice.get(id).getAndconfig() == null) {
	    		
	    			andConfig = new AndroidConfig();
	    		
	    	} else {
	    				andConfig = profileservice.get(id).getAndconfig();
	    			}

	    	model.addAttribute( "andConfig",  andConfig);
	      	model.addAttribute("profileAttribute", profileservice.get(id));
	    		
	    	return "androidconf";
		}
	    
	    @RequestMapping(value = "/profiles/edit/android", method = RequestMethod.POST)
	    public String saveAndroidConf(@ModelAttribute("andConfig") AndroidConfig andConfig, @ModelAttribute("profileAttribute") Profile profile, @RequestParam(value="id", required=true) Integer id) {
	    	logger.debug("Received request to update Android Config: ");
	    
	    	profile.setId(id);
	 	
	    	profileservice.editAndroidConfig(profile, andConfig);
	    	
			return "redirect:/switchboard/profiles";
		}
	    


	    @RequestMapping(value = "/profiles/edit/blackberry", method = RequestMethod.POST)
	    public String saveBlackBerryConf(@ModelAttribute("bbConfig") BlackBerryConfig bbConfig, @ModelAttribute("profileAttribute") Profile profile, @RequestParam(value="id", required=true) Integer id) {
	    	logger.debug("Received request to update BlackBerry Config");

	    	profile.setId(id);
	    	
	    	profileservice.editBlackBerryConfig( profile, bbConfig );
	    	
			return "redirect:/switchboard/profiles";
		}
	    
	    @RequestMapping(value = "/profiles/edit/blackberry", method = RequestMethod.GET)
	    public String getEditblackberryConf(@RequestParam(value="id", required=true) Integer id,  Model model) {
	    	logger.debug("Received request to show Android edit page");
	  
	    	BlackBerryConfig bbConfig;
	    	
	    	if(  profileservice.get(id).getBBconfig() == null) {
	    		bbConfig = new BlackBerryConfig();
	    	
	    	} else {
	    			bbConfig = profileservice.get(id).getBBconfig();
	    
	    		   }

	    	model.addAttribute( "bbConfig", bbConfig);
	      	model.addAttribute("profileAttribute", profileservice.get(id));
	      	
	    	return "blackberryconf";
		}

	   
	    
	    @RequestMapping(value = "/profiles/edit/iphone", method = RequestMethod.GET)
	    public String getEditiPhoneConf(@RequestParam(value="id", required=true) Integer id,  Model model) {
	    	logger.debug("Received request to show Android edit page");
	  
	    	AppleConfig appleConfig;
	    	
	    	if(  profileservice.get(id).getAppleconfig() == null) {
	    		appleConfig = new AppleConfig();
	    	
	    	} else {
	    		appleConfig =  profileservice.get(id).getAppleconfig();
	    
	    		   }

	    	model.addAttribute( "appleConfig", appleConfig);
	      	model.addAttribute("profileAttribute", profileservice.get(id));
	   
	    	return "iphoneconf";
		}
	    
	    
	    
	    @RequestMapping(value = "/profiles/edit/iphone", method=RequestMethod.POST, headers="Content-Type=multipart/form-data")
	    public String handleFormUpload(@ModelAttribute("appleConfig") AppleConfig appleConfig, @ModelAttribute("profileAttribute") Profile profile, @RequestParam(value="id", required=true) Integer id,
	    																		@RequestParam("password") String password,
	    																		@RequestParam("file") MultipartFile file,  Model model) {
	    	logger.debug("Received request to update iPhone Config");
	    	
	    	profile.setId(id);
	    	appleConfig.setApns_password(password);
	    	
	    	
	        if (!file.isEmpty()) {
	        	byte[] bytes = null;
	            try {
	            	appleConfig.setApns_keystore(file.getBytes());
					bytes = file.getBytes();
			    
				} catch (IOException e) {
					logger.info("error processing uploaded file", e);
				}
	        	logger.info("file upload received! Name:[" + file.getOriginalFilename() + "] Size:[" + bytes.length + "]");
	        	profileservice.editiPhoneConfig(appleConfig, profile);
	      
	       } else {
	    	   logger.info("error processing uploaded file");
	       }
	        return "redirect:/switchboard/profiles";
	    }
	    
	    

	    @RequestMapping(value = "/profiles/devicelist", method = RequestMethod.GET)
	    public String getDevicelist(@RequestParam(value="id", required=true) Integer id, Model model) {
	    	logger.debug("Received request to show list page");
	   
	    	Profile profile = profileservice.get(id);
	    
	    	List<Device> devices = new ArrayList<Device>();
	    	devices.addAll(profile.getDevices());
	    	
	    	// Attach device to the Model
	    	model.addAttribute("devices", devices);
	     	model.addAttribute("messageAttribute", new Notification());
	    	model.addAttribute("profileAttribute", profileservice.get(id));
	    	
	    	return "devicelist";
		}
	    
	  
	    @RequestMapping(value = "/profiles/devicelist", method = RequestMethod.POST)
	    public String sendMessage(@ModelAttribute("messageAttribute") Notification message, @RequestParam(value="id", required=true) Integer id,  Model model) {   	
	    	logger.debug("SendMessage: "+message.getMessage());
	    	
	    	Profile profile = profileservice.get(id);
	    	profileservice.sendMessages(profile, message);
	    	
			model.addAttribute("id", id);
			
			return "redirect:/switchboard/profiles/devicelist";
		}
	    
	
}

