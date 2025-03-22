package com.thiagoleite.GastroHubSolo.entrypoint.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserTypeResponseDTO {
    private Long id;
    private String name;
    private Date createdAt;
    private Date lastUpdatedAt;
}
