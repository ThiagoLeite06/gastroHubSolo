package com.thiagoleite.GastroHubSolo.core.usecase;

import com.thiagoleite.GastroHubSolo.core.domain.AuthResponseDomain;
import com.thiagoleite.GastroHubSolo.core.domain.LoginRequestDomain;

public interface AuthenticateUserUseCase {
    AuthResponseDomain execute(LoginRequestDomain loginRequestDomain);
}

