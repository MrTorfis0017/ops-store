package com.ops.auth.controllers;

import com.ops.common.dto.AddUserToGroupRequest;
import com.ops.common.dto.CreateGroupRequest;
import com.ops.common.exceptions.OpsErrorCode;
import com.ops.common.exceptions.OpsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('MASTER')")
@RequiredArgsConstructor
public class AdminController {

    private final JdbcUserDetailsManager userDetailsManager;

    @Operation(description = "Get user details using name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
            @ApiResponse(responseCode = "404", description = "ERROR|User not found")
    })
    @GetMapping("/users/{userName}")
    public UserDetails loadUser(@PathVariable String userName) {
        return userDetailsManager.loadUserByUsername(userName);
    }

    @Operation(description = "Get all groups")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
    })
    @GetMapping("/groups")
    public List<String> getAllGroups() {
        return userDetailsManager.findAllGroups();
    }

    @Operation(description = "Get all users from specific group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
            @ApiResponse(responseCode = "404", description = "ERROR|Group with this name is not exist")
    })
    @GetMapping("/groups/{groupName}/users")
    public List<String> getUsersInGroup(@PathVariable String groupName) {
        List<String> result = userDetailsManager.findUsersInGroup("GROUP_" + groupName);
        if (result.isEmpty()) {
            throw OpsException.errorWithDescription(OpsErrorCode.GROUP_NOT_EXIST, groupName);
        }
        return result;
    }

    @Operation(description = "Get all authorities from specific group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
            @ApiResponse(responseCode = "404", description = "ERROR|Group with this name is not exist")
    })
    @GetMapping("/groups/{groupName}/authorities")
    public List<GrantedAuthority> getGroupAuthorities(@PathVariable String groupName) {
        List<GrantedAuthority> result = userDetailsManager.findGroupAuthorities("GROUP_" + groupName);
        if (result.isEmpty()) {
            throw OpsException.errorWithDescription(OpsErrorCode.GROUP_NOT_EXIST, groupName);
        }
        return result;
    }

    @Operation(description = "Group create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
    })
    @PostMapping("/groups/create")
    public List<GrantedAuthority> createGroup(
            @RequestBody CreateGroupRequest createGroupRequest) {
        userDetailsManager.createGroup(
                "GROUP_" + createGroupRequest.getGroupName(),
                AuthorityUtils.createAuthorityList("ROLE_" + createGroupRequest.getRole())
        );
        return userDetailsManager.findGroupAuthorities("GROUP_" + createGroupRequest.getGroupName());
    }

    @Operation(description = "Add user to group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
    })
    @PostMapping("/groups/add")
    public List<String> addUserToGroup(
            @RequestBody AddUserToGroupRequest addUserToGroupRequest) {
        userDetailsManager.addUserToGroup(addUserToGroupRequest.getUsername(), "GROUP_" + addUserToGroupRequest.getGroupName());
        return getUsersInGroup(addUserToGroupRequest.getGroupName());
    }
}
