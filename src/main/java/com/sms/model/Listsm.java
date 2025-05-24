package com.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Listsm {
	
	private String from;
	@JsonProperty("to")
	private String myto;
	private String body;
	private String entityid;
	private String templateid;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMyto() {
		return myto;
	}

	public void setMyto(String myto) {
		this.myto = myto;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getEntityid() {
		return entityid;
	}

	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}

	public String getTemplateid() {
		return templateid;
	}

	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
}