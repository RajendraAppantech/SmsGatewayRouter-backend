package com.sms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sms.entity.CustomerDetails;
import com.sms.entity.SmsMaster;
import com.sms.entity.TemplateMaster;

@Repository
public class Repositories {

	public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, String> {

		CustomerDetails findByName(String name);
		CustomerDetails findByCustomerCode(String customerCode);
		CustomerDetails findByAuthKey(String authKey);
	}
	
	public interface SmsMasterRepository extends JpaRepository<SmsMaster, Long>{

		SmsMaster findBySmsKeyAndSendTxnId(String key, String txnId);
	}
	
	public interface TemplateMasterRepository extends JpaRepository<TemplateMaster, String>{

		TemplateMaster findByTemplateKey(String templateid);

	}
}
