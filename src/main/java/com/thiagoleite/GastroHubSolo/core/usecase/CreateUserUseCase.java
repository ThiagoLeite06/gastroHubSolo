package com.thiagoleite.GastroHubSolo.core.usecase;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;

public interface CreateUserUseCase {
    UserDomain execute(UserDomain userDomain);
}
