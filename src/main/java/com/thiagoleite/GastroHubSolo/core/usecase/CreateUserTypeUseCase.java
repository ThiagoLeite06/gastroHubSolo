package com.thiagoleite.GastroHubSolo.core.usecase;

import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;

public interface CreateUserTypeUseCase {
    UserTypeDomain execute(UserTypeDomain userTypeDomain);
}
