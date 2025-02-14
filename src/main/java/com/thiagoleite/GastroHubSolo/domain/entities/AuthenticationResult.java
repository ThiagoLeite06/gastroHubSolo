package com.thiagoleite.GastroHubSolo.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResult {
    private final User user;
    private final String token;

    public AuthenticationResult(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
