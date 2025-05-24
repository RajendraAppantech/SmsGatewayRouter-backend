package com.sms.model;

import java.util.ArrayList;

public class SendBulkSmsRequest {

	private String key;
	private ArrayList<Listsm> listsms;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public ArrayList<Listsm> getListsms() {
		return listsms;
	}
	public void setListsms(ArrayList<Listsm> listsms) {
		this.listsms = listsms;
	}
	
}
