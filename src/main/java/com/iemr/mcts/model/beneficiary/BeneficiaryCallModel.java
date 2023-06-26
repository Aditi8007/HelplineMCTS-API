package com.iemr.mcts.model.beneficiary;

import java.sql.Timestamp;

//import com.iemr.common.data.callhandling.CallType;
import com.iemr.mcts.model.user.OutboundCallRequestModel;

import lombok.Data;

public @Data class BeneficiaryCallModel
{
	private Long benCallID;
	private OutboundCallRequestModel outboundCallRequest;
	private Long beneficiaryRegID;
	private BeneficiaryModel i_beneficiary;
	private String callID;
	private Integer calledServiceID;
	private Boolean is1097;
	private Timestamp callTime;
	private String remarks;
	private String servicesProvided;
	private Integer callTypeID;
	//private CallType callTypeObj;
	private String callClosureType;
	private Integer dispositionStatusID;
	private Integer callReceivedUserID;
	private Integer callEndUserID;
	private String category;
	private String subCategory;
	private String cDICallStatus;
	private String agentID;
	private Boolean isOutbound;
	private Boolean isCalledEarlier = false;
	private Boolean deleted;
	private String createdBy;
	private Timestamp createdDate;
	private String modifiedBy;
	private Timestamp lastModDate;
	private Long informationServices;
	private Long feedbackServices;
	private Long referralServices;
	private Long counsellingServices;
	private Timestamp filterStartDate;
	private Timestamp filterEndDate;
	private Boolean fitToBlock = false;
	private String phoneNo;
	private String receivedRoleName;
	private Boolean endCall = false;
	private String agentIPAddress;
	private Long maxCount = 0l;
	private String recordingPath;
	private String archivePath;
	private Integer count;
}
