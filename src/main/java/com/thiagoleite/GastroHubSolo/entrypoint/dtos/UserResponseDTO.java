package com.thiagoleite.GastroHubSolo.entrypoint.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private Date lastUpdatedAt;
    private String address;
    private UserTypeResponseDTO userType;
}
