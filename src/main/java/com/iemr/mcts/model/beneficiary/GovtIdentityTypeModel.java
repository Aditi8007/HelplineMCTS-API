package com.iemr.mcts.model.beneficiary;

import java.util.List;

import com.iemr.mcts.data.domain.BenIdentityDTO;

import lombok.Data;

public @Data class GovtIdentityTypeModel
{
	private Integer govtIdentityTypeID;
	// private List<BeneficiaryModel> beneficiaryModels;
	private String identityType;
	private Boolean isGovtID;
	// private Boolean deleted;
	// private String createdBy;
	// private Timestamp createdDate;
	// private String modifiedBy;
	// private Timestamp lastModDate;

	public static GovtIdentityTypeModel createGovtIdentity(BenIdentityDTO benIdentityDTO)
	{
		GovtIdentityTypeModel govtIdentityType = new GovtIdentityTypeModel();
		if (benIdentityDTO != null)
		{
			govtIdentityType.govtIdentityTypeID = benIdentityDTO.getIdentityNameId();
			govtIdentityType.identityType = benIdentityDTO.getIdentityName();
			govtIdentityType.isGovtID = benIdentityDTO.getIsVerified();
		}
		return govtIdentityType;
	}

	public static GovtIdentityTypeModel createGovtIdentity(List<BenIdentityDTO> benIdentityDTOList)
	{
		GovtIdentityTypeModel govtIdentityType = new GovtIdentityTypeModel();
		if (benIdentityDTOList != null && benIdentityDTOList.size() > 0)
		{
			BenIdentityDTO benIdentityDTO = benIdentityDTOList.get(0);
			govtIdentityType.govtIdentityTypeID = benIdentityDTO.getIdentityNameId();
			govtIdentityType.identityType = benIdentityDTO.getIdentityName();

		}
		return govtIdentityType;
	}

	public static String getIdentityNo(List<BenIdentityDTO> benIdentityDTOList)
	{

		return benIdentityDTOList != null && benIdentityDTOList.size() > 0 ? benIdentityDTOList.get(0).getIdentityNo()
				: null;

	}

	public static Integer getIdentityTypeNo(List<BenIdentityDTO> benIdentityDTOList)
	{

		return benIdentityDTOList != null && benIdentityDTOList.size() > 0
				? benIdentityDTOList.get(0).getIdentityNameId() : null;

	}
}
