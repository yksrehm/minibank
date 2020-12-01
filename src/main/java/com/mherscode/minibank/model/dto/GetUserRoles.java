package com.mherscode.minibank.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mherscode.minibank.model.Role;

import java.util.HashSet;
import java.util.Set;

public class GetUserRoles {

    @JsonProperty("AVROLES")
    private Set<Role> AVROLES = new HashSet<>();
    @JsonProperty("USRROLES")
    private Set<Role> USRROLES = new HashSet<>();

    public GetUserRoles() {}

    public Set<Role> getAvRoles() {
        return AVROLES;
    }

    public void setAvRoles(Set<Role> avRoles) {
        this.AVROLES = avRoles;
    }

    public Set<Role> getUsrRoles() {
        return USRROLES;
    }

    public void setUsrRoles(Set<Role> usrRoles) {
        this.USRROLES = usrRoles;
    }
}
