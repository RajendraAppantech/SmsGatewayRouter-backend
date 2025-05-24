package com.sms.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "SMS_MASTER")
@DynamicInsert
@DynamicUpdate
@Table(name = "SMS_MASTER")
public class SmsMaster implements java.io.Serializable {

	private static final long serialVersionUID = -4689292691085871235L;
	private Long smsid;
	private String mobileNo;
	private String username;
	private String sms;
	private String otp;
	private String status;
	private Date otpDate;
	private String smsResponse;
	private String smsKey;
	private String smsFrom;
	private String templateId;
	private String entityId;
	private String sendTxnId;
	private String responseTxnId;
	private String bulkFileId;

	public SmsMaster() {
	}

	public SmsMaster(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public SmsMaster(String mobileNo, String sms, String otp, String status, Date otpDate, String smsResponse) {
		this.mobileNo = mobileNo;
		this.sms = sms;
		this.otp = otp;
		this.status = status;
		this.otpDate = otpDate;
		this.smsResponse = smsResponse;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SMSID", unique = true, nullable = false)
	public Long getSmsid() {
		return this.smsid;
	}

	public void setSmsid(Long smsid) {
		this.smsid = smsid;
	}

	@Column(name = "MOBILE_NO", nullable = false, length = 13)
	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "USERNAME", length = 100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "SMS", length = 1024)
	public String getSms() {
		return this.sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	@Column(name = "OTP", length = 32)
	public String getOtp() {
		return this.otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Column(name = "STATUS", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OTP_DATE", length = 23)
	public Date getOtpDate() {
		return this.otpDate;
	}

	public void setOtpDate(Date otpDate) {
		this.otpDate = otpDate;
	}

	@Column(name = "SMS_RESPONSE", length = 500)
	public String getSmsResponse() {
		return this.smsResponse;
	}

	public void setSmsResponse(String smsResponse) {
		this.smsResponse = smsResponse;
	}

	@Column(name = "SMS_KEY", length = 20)
	public String getSmsKey() {
		return smsKey;
	}

	public void setSmsKey(String smsKey) {
		this.smsKey = smsKey;
	}

	@Column(name = "SMS_FROM", length = 20)
	public String getSmsFrom() {
		return smsFrom;
	}

	public void setSmsFrom(String smsFrom) {
		this.smsFrom = smsFrom;
	}

	@Column(name = "TEMPLATE_ID", length = 50)
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	@Column(name = "ENTITY_ID", length = 50)
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	@Column(name = "SEND_TXN_ID", length = 50)
	public String getSendTxnId() {
		return sendTxnId;
	}

	public void setSendTxnId(String sendTxnId) {
		this.sendTxnId = sendTxnId;
	}

	@Column(name = "RESPONSE_TXN_ID", length = 50)
	public String getResponseTxnId() {
		return responseTxnId;
	}

	public void setResponseTxnId(String responseTxnId) {
		this.responseTxnId = responseTxnId;
	}

	@Column(name = "bulk_file_id")
	public String getBulkFileId() {
		return bulkFileId;
	}

	public void setBulkFileId(String bulkFileId) {
		this.bulkFileId = bulkFileId;
	}

}
