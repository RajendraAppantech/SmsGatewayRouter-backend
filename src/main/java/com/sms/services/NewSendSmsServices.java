package com.sms.services;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sms.constant.ErrorMessages;
import com.sms.entity.CustomerDetails;
import com.sms.entity.SmsMaster;
import com.sms.entity.TemplateMaster;
import com.sms.model.CommonResponse;
import com.sms.repository.Repositories.CustomerDetailsRepository;
import com.sms.repository.Repositories.SmsMasterRepository;
import com.sms.repository.Repositories.TemplateMasterRepository;

@Service
public class NewSendSmsServices {
	
	private static final Logger Logger = LoggerFactory.getLogger(NewSendSmsServices.class);
	private static final ThreadLocal<SimpleDateFormat> rrnSuffixFormat    = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyDDDHH"));
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	
	@Autowired
	private CustomerDetailsRepository detailsRepository;
	
	@Autowired
	private SmsMasterRepository smsMasterRepository;
	
	@Autowired
	private TemplateMasterRepository templateMasterRepository;

	public CommonResponse sendSingleSms(String key, String to, String templateid, String body, String fileId) {
		CommonResponse response = new CommonResponse();
		try {
			
			CustomerDetails master = detailsRepository.findByAuthKey(key);
			if (master == null) {
				response.setStatus(false);
				response.setMessage(ErrorMessages.INVALID_KEY);
				response.setRespCode("01");
				return response;
			}
			
			if(master.getSmsBalance() < 5) {
				response.setStatus(false);
				response.setMessage("Dear user, Your "+ErrorMessages.LOW_BALANCE);
				response.setRespCode("01");
				return response;
			}
			
			if(to.length() > 10) {
				response.setStatus(false);
				response.setMessage(ErrorMessages.INVALID_MOBILENO);
				response.setRespCode("01");
				return response;
			}
			
			System.out.println("templateid  " +templateid);
			
			TemplateMaster temp = templateMasterRepository.findByTemplateKey(templateid);
			if (temp == null) {
				response.setStatus(false);
				response.setMessage("Invalid Template ID");
				response.setRespCode("01");
				return response;
			}
			
			if(!master.getEntityId().equalsIgnoreCase(temp.getEntityId())) {
				response.setStatus(false);
				response.setMessage(ErrorMessages.INVALID_KEY +" Invalid entityID with user.");
				response.setRespCode("01");
				return response;
			}
			
			String txnId = master.getCustomerCode()+getTxnId();
			SmsMaster sms = new SmsMaster();
			sms.setMobileNo(to);
			sms.setUsername(master.getCustomerCode());
			sms.setSms(body);
			sms.setStatus("P");
			sms.setOtpDate(new Date());
			sms.setSmsKey(key);
			sms.setSmsFrom(temp.getSendorId());
			sms.setTemplateId(temp.getTemplateId());
			sms.setEntityId(temp.getEntityId());
			sms.setSmsResponse("SMS send pending for proccess");
			sms.setSendTxnId(txnId);
			sms.setBulkFileId(fileId);
			smsMasterRepository.save(sms);
			
			response.setStatus(true);
			response.setMessage("SMS send successfully...");
			response.setRespCode("00");
			response.setData("txnId", txnId);
			return response;
			
		} catch (Exception e) {
			e.printStackTrace();
			Logger.info("EXCEPTION : " + e);
			response.setStatus(false);
			response.setMessage(ErrorMessages.EXCEPTION);
			response.setRespCode("EX");
			return response;
		}
	}
	
	public String getTxnId() {
		return rrnSuffixFormat.get().format(new Date()).substring(3)+String.format("%06d", SECURE_RANDOM.nextInt(999999));
	}
}
