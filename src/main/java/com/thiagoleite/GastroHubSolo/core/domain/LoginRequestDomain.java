package com.thiagoleite.GastroHubSolo.core.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDomain {
    private String email;
    private String password;
}