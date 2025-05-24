package com.sms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.sms.client.OneExcelClient;
import com.sms.constant.ErrorMessages;
import com.sms.entity.CustomerDetails;
import com.sms.entity.SmsMaster;
import com.sms.model.CheckStatusResponse;
import com.sms.model.CommonResponse;
import com.sms.repository.Repositories.CustomerDetailsRepository;
import com.sms.repository.Repositories.SmsMasterRepository;

@Service
public class FetchBalanceService {
	
	private static final Logger Logger = LoggerFactory.getLogger(SendSmsServices.class);
	
	@Autowired
	private CustomerDetailsRepository detailsRepository;
	
	@Autowired
	private SmsMasterRepository smsMasterRepository;
	
	@Autowired
	private OneExcelClient oneExcelClient;

	public CommonResponse getBalance(String key) {
		CommonResponse response = new CommonResponse();
		try {
			
			CustomerDetails master = detailsRepository.findByAuthKey(key);
			if (master == null) {
				response.setStatus(false);
				response.setMessage(ErrorMessages.INVALID_KEY);
				response.setRespCode("01");
				return response;
			}
			
			response.setStatus(true);
			response.setMessage("success");
			response.setRespCode("00");
			response.setData("balance", master.getSmsBalance());
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

	public CommonResponse getTxnStatus(String key, String txnId) {
		CommonResponse response = new CommonResponse();
		try {
			SmsMaster master = smsMasterRepository.findBySmsKeyAndSendTxnId(key , txnId);
			if (master == null) {
				response.setStatus(false);
				response.setMessage("SMS transactions "+ErrorMessages.NOT_FOUND);
				response.setRespCode("01");
				return response;
			}
			
			if(Strings.isNullOrEmpty(master.getResponseTxnId())) {
				response.setStatus(false);
				response.setMessage("SMS pending for process");
				response.setRespCode("01");
				return response;
			}
			
			CheckStatusResponse checkTxn = oneExcelClient.checkSmsStatus(master.getResponseTxnId());
			if(checkTxn == null) {
				response.setStatus(false);
				response.setMessage("Connection timeout, Please try again later.");
				response.setRespCode("01");
				return response;
			}

			if(Strings.isNullOrEmpty(checkTxn.getDescription())) {
				response.setStatus(true);
				response.setMessage("success");
				response.setRespCode("00");
				response.setData("mobile", master.getMobileNo());
				response.setData("description", checkTxn.getResponse().get(0).getDescription());
				return response;
			}
			
			response.setStatus(false);
			response.setMessage("failed");
			response.setRespCode("01");
			response.setData("mobile", master.getMobileNo());
			response.setData("description", checkTxn.getDescription());
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
}
