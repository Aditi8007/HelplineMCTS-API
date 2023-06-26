package com.iemr.mcts.data.supervisor;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.mcts.data.agent.MctsOutboundCallDetail;
import com.iemr.mcts.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Table(name = "m_calltype")
@Data
public class CallType
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "CallTypeID")
	private Integer callTypeID;
	
	@Expose
	@Column(name = "CallGroupType")
	private String callGroupType;
	
	@Expose
	@Column(name = "CallType")
	private String callType;
	@Expose
	@Column(name = "CallTypeDesc")
	private String callTypeDesc;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;
	
	@Expose
	@Column(name = "IsInbound")
	private Boolean isInbound;
	@Expose
	@Column(name = "IsOutbound")
	private Boolean isOutbound;
	
	@Column(name = "Deleted", insertable = false, updatable = true)
	@Expose
	private Boolean deleted;
	
	@Column(name = "CreatedBy")
	@Expose
	private String createdBy;
	
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Timestamp createdDate;
	
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Timestamp lastModDate;
	
	/**
	 * mcts outbound detail to store and get history
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "callType")
	private List<MctsOutboundCallDetail> mctsOutboundCallDeatils;
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString()
	{
		return outputMapper.gson().toJson(this);
	}

	public String getCallGroupType()
	{
		return callGroupType;
	}

	public String getCallType()
	{
		return callType;
	}
}
