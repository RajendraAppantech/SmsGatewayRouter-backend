package com.sms.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckStatusResponse {

	private ArrayList<Response> response;
	private String status;
	private String description;

	public ArrayList<Response> getResponse() {
		return response;
	}

	public void setResponse(ArrayList<Response> response) {
		this.response = response;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
