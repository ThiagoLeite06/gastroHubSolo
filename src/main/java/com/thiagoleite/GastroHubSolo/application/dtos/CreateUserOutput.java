package com.thiagoleite.GastroHubSolo.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class CreateUserOutput {
    private Long id;
    private String name;
    private String email;
    private String role;
    private Date lastUpdatedAt;
    private String address;
    private UserTypeOutput userType;
}
