package com.iemr.mcts.model.user;

import java.sql.Date;
import java.util.List;

import com.iemr.mcts.model.userbeneficiary.Status;

import lombok.Data;

public @Data class ServiceProvider
{

	private Integer serviceProviderID;
	private List<ProviderServiceMappingModel> providerServiceMappingModels;
	private String serviceProviderName;
	private Date joiningDate;
	private Integer stateID;
	private String logoFileName;
	private String logoFilePath;
	private String primaryContactName;
	private String primaryContactNo;
	private String primaryContactEmailID;
	private String primaryContactAddress;
	private Date primaryContactValidityTillDate;
	private String secondaryContactName;
	private String secondaryContactNo;
	private String secondaryContactEmailID;
	private String secondaryContactAddress;
	private Date secondaryContactValidityTillDate;
	private Integer statusID;
	private Status status;
	private Date validFrom;
	private Date validTill;
	private Boolean deleted;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date lastModDate;
}
