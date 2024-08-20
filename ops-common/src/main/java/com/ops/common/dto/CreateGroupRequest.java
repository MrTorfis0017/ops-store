package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGroupRequest {

    private String groupName;

    private String role;
}
