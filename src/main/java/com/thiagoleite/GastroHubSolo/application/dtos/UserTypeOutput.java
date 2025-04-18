package com.thiagoleite.GastroHubSolo.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserTypeOutput {
    private Long id;
    private String name;
    private Date createdAt;
    private Date lastUpdatedAt;
}
