package org.switchboard.service;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.switchboard.domain.Device;
import org.switchboard.domain.config.AndroidConfig;



@Service("configService")
@Transactional
public class ConfigurationService {
	
	
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	

	
	
	
	
	
	
	
	
	
}
