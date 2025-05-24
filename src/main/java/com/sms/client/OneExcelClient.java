package com.sms.client;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sms.model.CheckStatusResponse;
import com.sms.model.GetSmsResponse;

@Service
public class OneExcelClient {

	private static final Logger logger = LoggerFactory.getLogger(OneExcelClient.class);

	@Value("${SMS_SERVICE_URL}")
	private String smsUrl;

	@Value("${SMS_KEY}")
	private String smsKey;

	@Value("${SMS_FROM}")
	private String smsFrom;

	private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(30000);
		clientHttpRequestFactory.setReadTimeout(30000);
		return clientHttpRequestFactory;
	}

	@Bean
	public RestTemplate getRestTemplateHsm() {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		restTemplate.setInterceptors(Collections.singletonList(new LoggingRequestInterceptor()));
		return restTemplate;
	}

	public GetSmsResponse getSmsBalance() {
		GetSmsResponse resp = new GetSmsResponse();
		try {
			HttpHeaders header = new HttpHeaders();
			header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(smsUrl + "bal");
			builder.queryParam("key", smsKey);
			resp = getRestTemplateHsm().getForObject(builder.build().toUri(), GetSmsResponse.class);
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("EXCEPTION : " + e);
			return null;
		}
	}
	
	public GetSmsResponse sendSingleSms() {
		GetSmsResponse resp = new GetSmsResponse();
		try {
			HttpHeaders header = new HttpHeaders();
			header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(smsUrl + "sms");
			builder.queryParam("key", smsKey);
			builder.queryParam("to", "7506545845");
			builder.queryParam("from", smsFrom);
			builder.queryParam("templateid", "1707170382803137315");
			builder.queryParam("entityid", "1201160819143415278");
			builder.queryParam("body",
					"Dear aman, You requested a password reset for your Appan Dukan Merchant account. Please use the following OTP to reset your password: OTP : 1234 Thank you for choosing Appan Dukan.");
			resp = getRestTemplateHsm().getForObject(builder.build().toUri(), GetSmsResponse.class);
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("EXCEPTION : " + e);
			return null;
		}
	}
	
	public CheckStatusResponse checkSmsStatus(String txnId) {
		CheckStatusResponse resp = new CheckStatusResponse();
		try {
			HttpHeaders header = new HttpHeaders();
			header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(smsUrl + "report");
			builder.queryParam("key", smsKey);
			builder.queryParam("uid", txnId);
			resp = getRestTemplateHsm().getForObject(builder.build().toUri(), CheckStatusResponse.class);
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("EXCEPTION : " + e);
			return null;
		}
	}
}
