package com.thiagoleite.GastroHubSolo.core.usecase;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;

public interface UpdateUserUseCase {
    UserDomain execute(Long id, UserDomain userDomain);
}
