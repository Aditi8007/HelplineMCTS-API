package com.iemr.mcts.model.user;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import lombok.Data;

public @Data class Role
{
	private Integer roleID;
	private String roleName;
	private String roleDesc;
	private Boolean deleted;
	private String createdBy;
	private Timestamp createdDate;
	private String modifiedBy;
	private Timestamp lastModDate;
	private Set<UserServiceRoleMappingModel> userServiceRoleMappingModels;
	private Set<NotificationModel> notificationModels;
	private List<ServiceRoleScreenMappingModel> serviceRoleScreenMappingModels;
}
