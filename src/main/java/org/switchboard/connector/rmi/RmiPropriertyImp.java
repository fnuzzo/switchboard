package org.switchboard.connector.rmi;

import net.rim.pushsdk.commons.PushSDKProperties;



public class RmiPropriertyImp implements PushSDKProperties{

	//private boolean publicpush=true;
	
	//# DTD declaration to use when constructing XML to send to the public/BIS PPG (this property is not related to parsing XML sent from the PPG).
	//# Important: This property should not be changed unless the PPG changes the DTD it is using first!
	//dtd.declaration.public=<!DOCTYPE pap PUBLIC "-//WAPFORUM//DTD PAP 2.1//EN" "http://www.openmobilealliance.org/tech/DTD/pap_2.1.dtd">
	private String dtd_declaration_public = "<!DOCTYPE pap PUBLIC \"-//WAPFORUM//DTD PAP 2.1//EN\" \"http://www.openmobilealliance.org/tech/DTD/pap_2.1.dtd\">";
	
	//# Push submission URL of the public/BIS push server. If you are not using a public/BIS PPG, this value should not be set.
	//public.ppg.address=${ppg.base.url}/mss/PD_pushRequest
	private String public_ppg_address="https://cp3004.pushapi.eval.blackberry.com/mss/PD_pushRequest";
	
	//# Maximum number of attempts to generate a unique push id before giving up
	//regenerate.pushid.max.attempts=5
	private  int regenerate_pushid_max_attempts =5;
	
	//# List of parser special characters that should be avoided when specifying values of PAP message elements.
	//# For example, the characters below are not allowed for the pushId element of the PAP XML.
	//parser.special.characters=&":<
	private char[]parser_special_characters = new char[]{'&','"',':','<'};
	
	//# The default deliver before timestamp is the current time plus this offset in milliseconds.
	//# NOTE: This is only used in the low-level PAP component, if no expiry date is specified through the API. 
	//# The high-level push uses the push application's defaultPushLiveTime, if not specified in the API through PushParameters.
	//default.deliver.before.timestamp.offset=3600000
	private int default_deliver_before_timestamp_offset=3600000;
	
	//# The max. number of results returned by a push request detail find operation. Making this value too large may exceed available system memory.
	//push.request.detail.find.max.results=100000
	private int push_request_detail_find_max_results=100000;
	
	//# The frequency (in seconds) with which push statistics are updated in storage.  All counts and content sums will be kept in memory until
	//# a batch update is done to storage at the specified interval.  As a result, there might be slight inconsistencies in the stats until
	//# the update takes place.  Setting a larger value for this property increases the chances for inconsistency.  Setting a smaller value 
	//# guarantees more regular updates, but will impact performance more significantly.
	//push.stats.update.frequency=120
	private int push_stats_update_frequency=120;
	
	//# The max. number of push statistics in the queue waiting to be sent for a batch update. 
	//# If the queue has reached its max. size, it will block until the size of the queue goes down again.
	//push.stats.update.queuesize=100000
	private int push_stats_update_queuesize=100000;
	
	//# Queue size to use during unsubscribes for a hard application delete.  
	//# The queue will block and wait if the threads working on the unsubscribes cannot keep up with the queue filling up.
	//pushapp.delete.unsubscribe.queuesize=100000
	private int pushapp_delete_unsubscribe_queuesize=100000;
	
	
	//# The amount of time to wait (in minutes) for the unsubscribing of subscribers   
	//# for a hard application delete before timing out and throwing an exception.
	//pushapp.delete.unsubscribe.timeout=30
	private int pushapp_delete_unsubscribe_timeout=30;

	//# The amount of time to wait (in minutes) for the deletion of subscribers
	//# for a hard application delete before timing out and throwing an exception.
	//pushapp.subscriber.delete.timeout=30
	private int pushapp_subscriber_delete_timeout=30;
	
//	############################################## 
//	# 	Subscription configuration properties 
//	##############################################
//	# Subscriber Deregistration URL for public/BIS push
//	# Note: If you are not using a public/BIS PPG, this value should not be set.
	//subscription.deregistration.url=https://cp3004.pushapi.eval.blackberry.com/mss/PD_cpDeregUser?pin=
	private String deregistration_url="https://cp3004.pushapi.eval.blackberry.com/mss/PD_cpDeregUser?pin=";
	
//	# Suspend Subscription URL for public/BIS push
//	# Note: If you are not using a public/BIS PPG, this value should not be set.
//	subscription.suspend.url=https://cp3004.pushapi.eval.blackberry.com/mss/PD_cpSub?cpAction=suspend&pin=
	private String suspend_url="https://cp3004.pushapi.eval.blackberry.com/mss/PD_cpSub?cpAction=suspend&pin=";
//	# Resume Subscription URL for public/BIS push
//	# Note: If you are not using a public/BIS PPG, this value should not be set.
//	subscription.resume.url=https://cp3004.pushapi.eval.blackberry.com/mss/PD_cpSub?cpAction=resume&pin=
	private String 	subscription_resume_url="https://cp3004.pushapi.eval.blackberry.com/mss/PD_cpSub?cpAction=resume&pin=";
//	# Subscription query URL for public/BIS push
//	# Note: If you are not using a public/BIS PPG, this value should not be set.
//	subscription.query.url=https://cp3004.pushapi.eval.blackberry.com/mss/PD_cpSubQuery
	private String subscription_query_url="https://cp3004.pushapi.eval.blackberry.com/mss/PD_cpSubQuery";
//	# The max. number of results returned by a subscriber find operation. Making this value too large may exceed available system memory.
//	subscription.find.max.results=100000
	private int subscription_find_max_results=100000;
//	# When the number of subscribers to validate are below this water mark an optimized query for small number of subscribers will be used; 
//	# conversely when above this number a second optimized query for a large number of subscribers will be used
//	subscription.validation.high.water.mark=100000
	private int subscription_validation_high_water_mark=100000;
//	# Number of subscribers to load at once from the database to validate. Making this number too large may exceed available system memory. 
//	# Making this number too small will decrease performance as more calls to the persistent store will be required.
//	# Important: This number should never be zero! One must be the minimum value
//	subscription.validation.batch.size=100000
    private int subscription_validation_batch_size=100000;
//	# Maximum number of threads to use for large subscription validation/subscription matching
//	subscription.matching.max.threads=5
    private int subscription_matching_max_threads=5;
//	# Queue size to use for large subscription validation/subscription matching
//	# The queue will block and wait if the threads working on the subscription validation/matching cannot keep up with the queue filling up.
//	subscription.matching.queuesize=50000
    private int subscription_matching_queuesize=50000;
//	# When syncing up the status of subscribers with the PPG, the batch size to use for each sync request.
//	# Important: The max. number of subscribers to include in each sync request is actually defined by the PPG.  
//	# It is present here in case it changes on the PPG end and has to be updated here on the SDK side. 
//	# Note: If you are not using a public/BIS PPG, this property will be ignored.
//	subscription.ppg.sync.batch.size=10000
    private int subscription_ppg_sync_batch_size=10000;
//	# Queue size to use for syncing up the status of subscribers with the PPG.
//	# The queue will block and wait if the threads working on the subscription status syncing cannot keep up with the queue filling up.
//	# Note: If you are not using a public/BIS PPG, this property will be ignored.
//	subscription.ppg.sync.queuesize=5000
    private int subscription_ppg_sync_queuesize=5000;
//
//	# The amount of time to wait (in minutes) for the syncing up of the status of subscribers with the PPG before timing out 
//	# and throwing an exception.
//	# Note: If you are not using a public/BIS PPG, this property will be ignored.
//	subscription.ppg.sync.timeout=30
    private int subscription_ppg_sync_timeout=30;
//
//	# Maximum number of threads to use for the syncing up of the status of subscribers with the PPG.
//	# Note: If you are not using a public/BIS PPG, this property will be ignored.
//	subscription.ppg.sync.threads=5
    private int subscription_ppg_sync_threads=5;
	
//    ############################################## 
//    #	HTTP client configuration properties 
//    ##############################################
//    # Connection timeout in milliseconds
//    http.connection.timeout=60000
    private int  http_connection_timeout=60000;
//
//    # Read timeout in milliseconds
//    http.read.timeout=120000
    private int   http_read_timeout=120000;
//
//    # Whether to use persistent connections (true or false)
//    http.is.persistent=true
    private boolean http_is_persistent=false;

	
	
	
	
	
	public int getAcknowledgementBatchSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getAcknowledgementMaxQueueSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getAcknowledgementMaxThreads() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getAcknowledgementPushLookupRetryDelay() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getDatabaseType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public long getDefaultDeliverBeforeTimestampOffset() {
		// TODO Auto-generated method stub
		return default_deliver_before_timestamp_offset;
	}

	
	public String getDtdDeclarationEnterprise() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getDtdDeclarationPublic() {
		// TODO Auto-generated method stub
		return dtd_declaration_public;
	}

	
	public String getEnterprisePpgAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getHttpConnectionTimeout() {
		// TODO Auto-generated method stub
		return http_connection_timeout;
	}

	
	public boolean getHttpIsPersistent() {
		// TODO Auto-generated method stub
		return http_is_persistent;
	}

	
	public int getHttpReadTimeout() {
		// TODO Auto-generated method stub
		return http_read_timeout;
	}

	
	public int getMaxInClauseValues() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public char[] getParserSpecialCharacters() {
		// TODO Auto-generated method stub
		return parser_special_characters;
	}

	
	@Deprecated
	public String getPpgAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getPublicPpgAddress() {
		// TODO Auto-generated method stub
		return public_ppg_address;
	}

	
	public int getPushAppDeleteUnsubscribeQueueSize() {
		// TODO Auto-generated method stub
		return pushapp_delete_unsubscribe_queuesize;
	}

	
	public int getPushAppDeleteUnsubscribeTimeout() {
		// TODO Auto-generated method stub
		return pushapp_delete_unsubscribe_timeout;
	}

	
	public int getPushAppSubscriberDeleteTimeout() {
		// TODO Auto-generated method stub
		return pushapp_subscriber_delete_timeout;
	}

	
	public int getPushRequestDetailFindMaxResults() {
		// TODO Auto-generated method stub
		return push_request_detail_find_max_results;
	}

	
	public int getPushStatsUpdateFrequency() {
		// TODO Auto-generated method stub
		return push_stats_update_frequency;
	}

	
	public int getPushStatsUpdateQueueSize() {
		// TODO Auto-generated method stub
		return push_stats_update_queuesize;
	}

	
	public int getRegeneratePushIdMaxAttempts() {
		// TODO Auto-generated method stub
		return regenerate_pushid_max_attempts;
	}

	
	public String getSubscriptionDeregistrationUrl() {
		// TODO Auto-generated method stub
		return deregistration_url;
	}

	
	public int getSubscriptionFindMaxResults() {
		// TODO Auto-generated method stub
		return subscription_find_max_results;
	}

	
	public int getSubscriptionMatchingMaxThreads() {
		// TODO Auto-generated method stub
		return subscription_matching_max_threads;
	}

	
	public int getSubscriptionMatchingQueueSize() {
		// TODO Auto-generated method stub
		return subscription_matching_queuesize;
	}

	
	public int getSubscriptionPPGSyncBatchSize() {
		// TODO Auto-generated method stub
		return subscription_ppg_sync_batch_size;
	}

	
	public int getSubscriptionPPGSyncMaxThreads() {
		// TODO Auto-generated method stub
		return subscription_ppg_sync_threads;
	}

	
	public int getSubscriptionPPGSyncQueueSize() {
		// TODO Auto-generated method stub
		return subscription_ppg_sync_queuesize;
	}

	
	public int getSubscriptionPPGSyncTimeout() {
		// TODO Auto-generated method stub
		return subscription_ppg_sync_timeout;
	}

	
	public String getSubscriptionQueryUrl() {
		// TODO Auto-generated method stub
		return subscription_query_url;
	}

	
	public String getSubscriptionResumeUrl() {
		// TODO Auto-generated method stub
		return subscription_resume_url;
	}

	
	public String getSubscriptionSuspendUrl() {
		// TODO Auto-generated method stub
		return suspend_url;
	}

	
	public int getSubscriptionValidationBatchSize() {
		// TODO Auto-generated method stub
		return subscription_validation_batch_size;
	}

	
	public int getSubscriptionValidationHighWaterMark() {
		// TODO Auto-generated method stub
		return subscription_validation_high_water_mark;
	}

	
	public String getWebsignalsDeregistrationUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getWebsignalsRegistrationUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getWebsignalsResumeUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getWebsignalsSuspendUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Deprecated
	public boolean isUsingPublicPush() {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Deprecated
	public boolean isUsingXmlParserDtdValidation() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void setAcknowledgementBatchSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setAcknowledgementMaxQueueSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setAcknowledgementMaxThreads(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setAcknowledgementPushLookupRetryDelay(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setDatabaseType(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setDefaultDeliverBeforeTimestampOffset(long arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setDtdDeclarationEnterprise(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setDtdDeclarationPublic(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setEnterprisePpgAddress(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setHttpConnectionTimeout(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setHttpIsPersistent(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setHttpReadTimeout(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setMaxInClauseValues(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setParserSpecialCharacters(char[] arg0) {
		// TODO Auto-generated method stub
		
	}

	
	@Deprecated
	public void setPpgAddress(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setPublicPpgAddress(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setPushAppDeleteUnsubscribeQueueSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setPushAppDeleteUnsubscribeTimeout(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setPushAppSubscriberDeleteTimeout(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setPushRequestDetailFindMaxResults(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setPushStatsUpdateFrequency(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setPushStatsUpdateQueueSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setRegeneratePushIdMaxAttempts(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionDeregistrationUrl(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionFindMaxResults(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionMatchingMaxThreads(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionMatchingQueueSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionPPGSyncBatchSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionPPGSyncMaxThreads(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionPPGSyncQueueSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionPPGSyncTimeout(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionQueryUrl(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionResumeUrl(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionSuspendUrl(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionValidationBatchSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setSubscriptionValidationHighWaterMark(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	@Deprecated
	public void setUsingPublicPush(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	
	@Deprecated
	public void setUsingXmlParserDtdValidation(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setWebsignalsDeregistrationUrl(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setWebsignalsRegistrationUrl(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setWebsignalsResumeUrl(String arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void setWebsignalsSuspendUrl(String arg0) {
		// TODO Auto-generated method stub
		
	}

}
