package com.sms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sms.model.CommonResponse;
import com.sms.services.FetchBalanceService;
import com.sms.services.NewSendSmsServices;
import com.sms.services.SendSmsServices;

@RestController
@RequestMapping("api/")
public class SmsController {

	@Autowired
	private SendSmsServices sendSmsServices;
	
	@Autowired
	private NewSendSmsServices newSendSmsServices;
	
	@Autowired
	private FetchBalanceService fetchBalanceService;
	
	@GetMapping("/sms")
	public CommonResponse singleSmsResp(
			@RequestParam("key") String key ,
			@RequestParam("to") String to, 
			@RequestParam("from") String from,
			@RequestParam("templateid") String templateid, 
			@RequestParam("entityid") String entityid , 
			@RequestParam("body") String body,
			@RequestParam(defaultValue = "") String fileId) {
		return sendSmsServices.sendSingleSms(key , to, from, templateid, entityid , body , fileId);
	}
	
	@GetMapping("/sendSms")
	public CommonResponse singleSmsResp(
			@RequestParam("key") String key ,
			@RequestParam("to") String to, 
			@RequestParam("templateid") String templateid, 
			@RequestParam("body") String body,
			@RequestParam(defaultValue = "") String fileId) {
		return newSendSmsServices.sendSingleSms(key , to, templateid , body,fileId);
	}
	
	@PostMapping("/bulksms")
	public CommonResponse sendBulkSms(@RequestParam("key") String key) {
		return fetchBalanceService.getBalance(key);
	}
	
	@GetMapping("/checkStatus")
	public CommonResponse getTxnStatus(@RequestParam("key") String key , @RequestParam("txnId") String txnId) {
		return fetchBalanceService.getTxnStatus(key , txnId);
	}
	
	@GetMapping("/bal")
	public CommonResponse getBalance(@RequestParam("key") String key) {
		return fetchBalanceService.getBalance(key);
	}
	
	/*@GetMapping("/report")
	public CommonResponse getReport(@PathParam("key") String key , 
			@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
		return fetchBalanceService.getReport(key , txnId);
	}*/
}
