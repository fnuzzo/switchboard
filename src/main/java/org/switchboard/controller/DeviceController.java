package org.switchboard.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.switchboard.service.ClientService;
import org.switchboard.service.DeviceService;


@Controller
@RequestMapping("/")
public class DeviceController {

	protected static Logger logger = Logger.getLogger("controller");

	
	@Resource(name="deviceService")
	DeviceService deviceService;
	
	
	
	@RequestMapping(value="/request", method=RequestMethod.POST, headers="Accept=application/json")
	//@ResponseStatus(HttpStatus.OK)
	public  @ResponseBody String registerJSON(@RequestBody String body) {
		String result= null;
		logger.info("new device register token: " + body);
				
			result = deviceService.request( body );
		return result;		
	}

}
