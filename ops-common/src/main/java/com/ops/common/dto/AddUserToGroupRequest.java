package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserToGroupRequest {

    private String username;

    private String groupName;
}
